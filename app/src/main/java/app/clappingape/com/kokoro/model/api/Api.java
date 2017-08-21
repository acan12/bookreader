package app.clappingape.com.kokoro.model.api;

import android.content.Context;

import app.clappingape.com.kokoro.App;
import app.clappingape.com.kokoro.model.api.response.MultipleResponse;
import app.clappingape.com.kokoro.presentation.di.IApi;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class Api extends BaseApi {

    public static void getApiSources(Context context, Callback callback) {
        IApi api = App.getComponent(context).getApi();
        api.getApiService().callApiSources("en").enqueue((retrofit2.Callback<MultipleResponse>) callback);
    }

}
