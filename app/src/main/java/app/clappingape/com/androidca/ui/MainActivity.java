package app.clappingape.com.androidca.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.clappingape.com.androidca.IConfig;
import app.clappingape.com.androidca.R;
import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import app.clappingape.com.androidca.model.dao.BaseDao;
import app.clappingape.com.androidca.model.dao.SourceDao;
import app.clappingape.com.androidca.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;


public class MainActivity extends BaseActivity {
    @BindView(R.id.payment_button)
    Button paymentButton;

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showProgressDialogOnDAOCalled(new SourceDao(this) {
            @Override
            public void call() {
                this.getSourcesDAO(MainActivity.this, BaseDao.getInstance(MainActivity.this, IConfig.KEY_CALLER_API_SOURCE).callback);
            }
        });


        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onApiResponseCallback(MultipleResponse mr, String callbackKey) {

        switch (callbackKey) {
//            case IConfig.KEY_CALLER_API_SOURCE:
//                Toast.makeText(this, IConfig.KEY_CALLER_API_SOURCE, Toast.LENGTH_LONG).show();
//
//                showProgressDialogOnDAOCalled(new SourceDao(this) {
//                    @Override
//                    public void call() {
//                        this.getSourcesDAO(MainActivity.this, BaseDao.getInstance(MainActivity.this, IConfig.KEY_CALLER_API_ARTICLE).callback);
//                    }
//                });
//
//                break;
//            case IConfig.KEY_CALLER_API_ARTICLE:
//                Toast.makeText(this, IConfig.KEY_CALLER_API_ARTICLE, Toast.LENGTH_LONG).show();
//                break;
            default:
                // line default code
                if (mr.getStatus().equals("ok")) {
                    Toast.makeText(this, "Status: OK, Size= " + mr.getSources().size(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Status: 200, but error", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    protected void onApiFailureCallback(String message) {
        Log.e("ERROR: ", message);
    }


}
