package app.clappingape.com.androidca.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import app.clappingape.com.androidca.R;
import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import app.clappingape.com.androidca.model.dao.BaseDao;
import app.clappingape.com.androidca.model.dao.SourceDao;
import app.clappingape.com.androidca.ui.base.BaseActivity;


public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProgressDialogOnDAOCalled(new SourceDao(this) {
            @Override
            public void call() {
                this.getSourcesDAO(MainActivity.this, BaseDao.getInstance(MainActivity.this).callback);
            }
        });

    }


    @Override
    protected void onApiCallbackResponse(MultipleResponse mr) {
        if (mr.getStatus().equals("ok")) {
            Toast.makeText(this, "Status: OK, Size= " + mr.getSources().size(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Status: 200, but error", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onApiCallbackFailure(String message) {
        Log.e("ERROR: ", message);
    }


}
