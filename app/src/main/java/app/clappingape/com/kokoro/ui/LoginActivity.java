package app.clappingape.com.kokoro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import app.clappingape.com.kokoro.R;
import app.clappingape.com.kokoro.ui.base.BaseActivity;

/**
 * Created by arysuryawan on 8/6/17.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
