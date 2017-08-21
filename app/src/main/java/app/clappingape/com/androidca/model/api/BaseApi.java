package app.clappingape.com.androidca.model.api;


import android.content.Context;

import app.clappingape.com.androidca.App;
import app.clappingape.com.androidca.presentation.di.IApi;

/**
 * Created by arysuryawan on 8/18/17.
 */

public class BaseApi {

    protected static ApiService setupApi(Context context) {

        IApi api = App.getComponent(context).getApi();
        return api.getApiService();
    }
}

