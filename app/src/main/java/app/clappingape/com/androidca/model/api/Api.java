package app.clappingape.com.androidca.model.api;

import android.content.Context;

import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class Api extends BaseApi {

    public static void doApiSources(Context context, Callback callback) {
        setupApi(context).callApiSources("en").enqueue((Callback<MultipleResponse>) callback);
    }

}
