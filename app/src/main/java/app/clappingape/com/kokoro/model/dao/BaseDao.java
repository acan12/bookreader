package app.clappingape.com.kokoro.model.dao;

import android.app.Activity;

import java.io.IOException;

import app.clappingape.com.kokoro.ui.base.BaseActivity;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

class BaseDao {

    private static BaseActivity ac;

    public BaseDao(BaseActivity ac) {
        this.ac = ac;
    }

    protected static Callback callback = new Callback() {
        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            ac.onCallbackResponse(call, response);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            ac.onCallbackFailure(t);
        }
    };


}
