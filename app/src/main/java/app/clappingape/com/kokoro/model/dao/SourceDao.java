package app.clappingape.com.kokoro.model.dao;

import java.util.List;

import app.clappingape.com.kokoro.model.Source;
import app.clappingape.com.kokoro.model.api.Api;
import app.clappingape.com.kokoro.ui.base.BaseActivity;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class SourceDao extends BaseDao {

    public SourceDao(BaseActivity ac) {
        super(ac);
    }

    public void getSourcesDAO(BaseActivity ac){
        Api.getApiSources(ac, callback);
    }
}
