package app.clappingape.com.kokoro.model.api;

import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arysuryawan on 8/18/17.
 */

public interface IApi {

    @GET("sources")
    Call<Source> getSources(@Query("language=en") @Nullable String language);

    @GET("articles")
    Call<Article> getArticles(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

}
