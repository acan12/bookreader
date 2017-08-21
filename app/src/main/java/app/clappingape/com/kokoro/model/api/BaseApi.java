package app.clappingape.com.kokoro.model.api;


import android.content.Context;

import app.clappingape.com.kokoro.App;
import app.clappingape.com.kokoro.presentation.di.IApi;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class BaseApi {

    protected static ApiService setupApi(Context context) {

        IApi api = App.getComponent(context).getApi();
        return api.getApiService();
    }
}

