package app.clappingape.com.androidca.presenter.dao;

import app.clappingape.com.androidca.ui.base.BaseActivity;
import app.clappingape.com.androidca.ui.base.BaseFragment;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao {

    private String callbackKey;

    private static BaseActivity ac = null;
    private static BaseFragment fm = null;



    public BaseDao(Object obj) {
        if (obj instanceof BaseActivity) this.ac = ac;
        if (obj instanceof BaseFragment) this.fm = fm;
    }

    private BaseDao(Object obj, String keyCallback) {
        if (obj instanceof BaseActivity) {
            this.ac = (BaseActivity) obj;
            this.callbackKey = keyCallback;
        }
        if (obj instanceof BaseFragment) {
            this.fm = (BaseFragment) obj;
            this.callbackKey = keyCallback;
        }
    }


    public static BaseDao getInstance(Object current) {
        return getInstance(current, "");
    }

    public static BaseDao getInstance(Object current, String key) {
        if (current instanceof BaseActivity)
            return new BaseDao((BaseActivity) current, key);
        else if (current instanceof BaseFragment)
            return new BaseDao((BaseFragment) current, key);
        return null;
    }

    public Callback callback = new Callback() {

        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if (ac != null)
                BaseActivity.onResponseCallback(call, response, ac, callbackKey);
            else
                BaseFragment.onResponseCallback(call, response, fm, callbackKey);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if (ac != null)
                BaseActivity.onFailureCallback(t, ac);
            else
                BaseFragment.onFailureCallback(t, fm);
        }
    };


    public void call() {}
}
