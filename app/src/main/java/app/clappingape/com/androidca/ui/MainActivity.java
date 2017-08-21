package app.clappingape.com.androidca.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import app.clappingape.com.androidca.R;
import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import app.clappingape.com.androidca.model.dao.SourceDao;
import app.clappingape.com.androidca.ui.base.BaseActivity;


public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SourceDao dao = new SourceDao(this);
        dao.getSourcesDAO(this);
    }


    @Override
    protected void apiCallbackResponse(MultipleResponse mr) {
        if(mr.getStatus().equals("ok")){
            Toast.makeText(this, "Status: OK, Size= "+mr.getSources().size(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Status: 200, but error", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void apiCallbackFailure(String message) {
        Log.e("ERROR: ", message);
    }



}
