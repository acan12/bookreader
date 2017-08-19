package app.clappingape.com.kokoro.model.api;

import app.clappingape.com.kokoro.model.api.response.MultipleResponse;
import app.clappingape.com.kokoro.ui.base.BaseActivity;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class Api extends BaseApi {

    public static void getApiSources(final BaseActivity ac, Callback callback) {
        initAPI().callApiSources("en")
                .enqueue((retrofit2.Callback<MultipleResponse>) callback);
    }

}
