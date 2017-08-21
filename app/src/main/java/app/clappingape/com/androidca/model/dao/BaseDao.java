package app.clappingape.com.androidca.model.dao;

import app.clappingape.com.androidca.ui.base.BaseActivity;
import app.clappingape.com.androidca.ui.base.BaseFragment;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

class BaseDao {

    private static BaseActivity ac;
    private static BaseFragment fm;

    public BaseDao(BaseActivity ac) {
        this.ac = ac;
    }
    public BaseDao(BaseFragment fm) {
        this.fm = fm;
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

    protected static Callback callbackFragment = new Callback() {
        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            fm.onCallbackResponse(call, response);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            fm.onCallbackFailure(t);
        }
    };


}
