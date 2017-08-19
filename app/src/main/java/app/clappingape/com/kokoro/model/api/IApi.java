package app.clappingape.com.kokoro.model.api;

import app.clappingape.com.kokoro.model.api.response.MultipleResource;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by arysuryawan on 8/18/17.
 */
public interface IApi {


    @Headers({
            "Cache-Control: no-cache",
            "Cache-Control: no-store",
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("sources")
    Call<MultipleResource> callApiSources(@Query("language") String language);

    @Headers({
            "Cache-Control: no-cache",
            "Cache-Control: no-store",
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("articles")
    Call<MultipleResource> callApiArticles(@Query("source") String sourceId, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

}
