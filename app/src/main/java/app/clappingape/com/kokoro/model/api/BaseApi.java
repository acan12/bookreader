package app.clappingape.com.kokoro.model.api;


import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.clappingape.com.kokoro.App;
import app.clappingape.com.kokoro.IConfig;
import app.clappingape.com.kokoro.presentation.di.IApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by arysuryawan on 8/18/17.
 */

public class BaseApi {

    protected static ApiService setupApi(Context context) {

        IApi api = App.getComponent(context).getApi();
        return api.getApiService();
    }
}

