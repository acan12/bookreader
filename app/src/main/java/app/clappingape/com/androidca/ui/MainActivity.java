package app.clappingape.com.androidca.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

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
        RxCompositeDisposableManager.doAction(new RxCompositeDisposableManager.OnProcess(){
            @Override
            public void onCall() {

                curlView.setSizeChangedObserver(new SizeChangedObserver());
                content = getTextFromUrl("https://www.getscoop.com/id/terms?lite=true").trim();

            }
        }, new RxCompositeDisposableManager.RxCallback(){
            @Override
            public void onComplete() {

//                String[] contents = content.split("\\r?\\n");
                int page = 32;
                int num = content.length()/page;
                String[] contents = new String[page];//content.split("(?<=\\G.{"+num+"})");
                int[] layouts = new int[page];

                for(int i=0; i<page; i++){
                    layouts[i] = R.layout.sample;
                    contents[i] = getSplitContent(i, num, content);
                }
                pageProvider = new PageProvider(layouts, contents);
                curlView.setPageProvider(pageProvider);
                curlView.setCurrentIndex(finalIndex, MainActivity. this);
                curlView.setBackgroundColor(0xFF202830);
            }
        });

    }

    private String getSplitContent(int i, int num, String content) {
        String src = content.substring(i*num, (i+1)*num);

        return src;
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
            Log.d("index", String.valueOf(index));
            View v = inflater.inflate(mLayouts[index], null);
            TextView web = (TextView) v.findViewById(R.id.content_layout);
            web.setText(Html.fromHtml(html));
//            web.setText(Html.fromHtml(getResources().getString(R.string.html_page_content, (index + 2) + "")));
//            web.loadUrl("https://www.getscoop.com/id/terms?lite=true");

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
                curlView.setMargins(.1f, .05f, .1f, .05f);
            } else {
                curlView.setViewMode(CurlViewComponent.SHOW_ONE_PAGE);
                curlView.setMargins(.1f, 0, 0, 0);
            }
        }
    }





}
