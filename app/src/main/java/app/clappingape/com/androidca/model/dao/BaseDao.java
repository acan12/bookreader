package app.clappingape.com.androidca.model.dao;

import app.clappingape.com.androidca.ui.base.BaseActivity;
import app.clappingape.com.androidca.ui.base.BaseFragment;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao {

    private static BaseActivity ac=null;
    private static BaseFragment fm=null;

    public BaseDao(BaseActivity ac) {
        this.ac = ac;
    }
    public BaseDao(BaseFragment fm) {
        this.fm = fm;
    }

    public static BaseDao getInstance(BaseActivity ac){
        return new BaseDao(ac);
    }

    public static BaseDao getInstance(BaseFragment fm){
        return new BaseDao(fm);
    }


    public Callback callback = new Callback() {
        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if(ac!=null)
                BaseActivity.onCallbackResponse(call, response, ac);
            else
                BaseFragment.onCallbackResponse(call, response, fm);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if(ac!=null)
                BaseActivity.onCallbackFailure(t, ac);
            else
                BaseFragment.onCallbackFailure(t, fm);
        }
    };


}
