package app.clappingape.com.kokoro.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import app.clappingape.com.kokoro.R;
import app.clappingape.com.kokoro.model.api.response.MultipleResponse;
import app.clappingape.com.kokoro.model.dao.SourceDao;
import app.clappingape.com.kokoro.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
