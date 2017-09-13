package app.clappingape.com.androidca.presenter.dao;

import app.clappingape.com.androidca.model.api.Api;
import app.clappingape.com.androidca.presenter.dao.BaseDao;
import app.clappingape.com.androidca.ui.base.BaseActivity;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class SourceDao extends BaseDao {

    public SourceDao(BaseActivity ac) {
        super(ac);
    }

    public void getSourcesDAO(BaseActivity ac, Callback callback) {
        Api.doApiSources(ac, callback);
    }
}
