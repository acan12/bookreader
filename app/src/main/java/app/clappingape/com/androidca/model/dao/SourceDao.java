package app.clappingape.com.androidca.model.dao;

import app.clappingape.com.androidca.model.api.Api;
import app.clappingape.com.androidca.ui.base.BaseActivity;
import support.component.DialogComponent;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class SourceDao extends BaseDao {

    public SourceDao(BaseActivity ac) {
        super(ac);
    }

    public void getSourcesDAO(BaseActivity ac) {
        DialogComponent.showProgressDialog(ac);
        Api.doApiSources(ac, callback);
    }
}
