package app.clappingape.com.androidca.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.clappingape.com.androidca.R;
import app.clappingape.com.androidca.ui.component.curl_view.CurlPage;
import app.clappingape.com.androidca.ui.component.curl_view.CurlViewComponent;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import support.rx.RxCompositeDisposableManager;

/**
 * Created by arysuryawan on 9/21/17.
 */

public class MainActivity extends Activity {
    @BindView(R.id.curl_view)
    CurlViewComponent curlView;

    PageProvider pageProvider;
    String content = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        int index = 0;

        if (getLastNonConfigurationInstance() != null) {
            index = (Integer) getLastNonConfigurationInstance();
        }


        final int finalIndex = index;
        RxCompositeDisposableManager.doAction(new RxCompositeDisposableManager.OnProcess() {
            @Override
            public void onCall() {

                curlView.setSizeChangedObserver(new SizeChangedObserver());
                content = getTextFromUrl("https://www.getscoop.com/id/terms?lite=true").trim();

            }
        }, new RxCompositeDisposableManager.RxCallback() {
            @Override
            public void onComplete() {

                String[] contents = getSplitContent(content, getWindowManager());
                int[] layouts = new int[contents.length];

                int i = 0;
                while (i < contents.length) {
                    layouts[i] = (i == 0) ? R.layout.cover : R.layout.content;
                    i++;
                }
                pageProvider = new PageProvider(layouts, contents);
                curlView.setPageProvider(pageProvider);
                curlView.setCurrentIndex(finalIndex, MainActivity.this);
                curlView.setBackgroundColor(0xFF202830);
            }
        });

    }


    @Override
    public void onPause() {
        super.onPause();
        curlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        curlView.onResume();
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return curlView.getCurrentIndex();
    }

    private String getTextFromUrl(String urlContent) {

        String fullString = "";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlContent)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                fullString = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullString;
    }


    private static int getTextOccurences(String source, String sentence) {
        int occurrences = 0;

        if (source.contains(sentence)) {
            int withSentenceLength = source.length();
            int withoutSentenceLength = source.replace(sentence, "").length();
            occurrences = (withSentenceLength - withoutSentenceLength) / sentence.length();
        }

        return occurrences;
    }

    private static String[] getSplitContent(String source, WindowManager windowManager) {
        List<String> results = new ArrayList<String>();
        int maxlimit = getFixContentLength(windowManager);
        int startIndex = 0;
        int endIndex = maxlimit;

        results.add("Book Reader");
        while (startIndex < source.length()) {
            String hasil = "";
            int down = endIndex;
            while (source.charAt(down) != ' ' && down > startIndex) {
                down--;
                if (source.charAt(down) == ' ') {
                    endIndex = down;
                    break;
                }
            }
            hasil = ((source.length() - endIndex) < maxlimit) ? source.substring(startIndex) : source.substring(startIndex, endIndex);
            hasil = (getTextOccurences(hasil, "<p>") > getTextOccurences(hasil, "</p>")) ? hasil + "</p>" : hasil;
            hasil = (getTextOccurences(hasil, "<p>") < getTextOccurences(hasil, "</p>")) ? "<p>" + hasil : hasil;

            startIndex = endIndex + 1;
            endIndex = ((endIndex + maxlimit) <= source.length()) ? endIndex + maxlimit : source.length() - 1;

            if (startIndex > endIndex) break;
            results.add(hasil);

        }
        return (String[]) results.toArray(new String[results.size()]);
    }

    private static int getFixContentLength(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.densityDpi;

        return height * 3;
    }


    private class PageProvider implements CurlViewComponent.PageProvider {

        private int[] mLayouts;
        private String[] contents;

        public PageProvider(int[] mLayouts, String[] contents) {
            this.mLayouts = mLayouts;
            this.contents = contents;
        }

        @Override
        public int getPageCount() {
            return mLayouts.length;
        }

        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {

            Bitmap front = loadBitmap(contents[index], width, height, index);
            page.setTexture(front, CurlPage.SIDE_BOTH);
            page.setColor(Color.argb(127, 255, 255, 255),
                    CurlPage.SIDE_BACK);

        }


        private Bitmap loadBitmap(String html, int width, int height, int index) {
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v = inflater.inflate(mLayouts[index], null);

            if (index == 0) {
                TextView titleCover = (TextView) v.findViewById(R.id.cover_layout);
                titleCover.setText(html);
            } else {

                TextView web = (TextView) v.findViewById(R.id.content_layout);
                web.setText(Html.fromHtml(html));
                TextView page = (TextView) v.findViewById(R.id.page);
                page.setText((index) + " of " + (mLayouts.length - 1));
            }
            v.measure(
                    View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight()
                    , Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.draw(c);
            return b;
        }
    }


    private class SizeChangedObserver implements CurlViewComponent.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                curlView.setViewMode(CurlViewComponent.SHOW_TWO_PAGES);
                curlView.setMargins(.1f, 0, .1f, 0);
            } else {
                curlView.setViewMode(CurlViewComponent.SHOW_ONE_PAGE);
                curlView.setMargins(.1f, 0, 0, 0);
            }
        }
    }


}
