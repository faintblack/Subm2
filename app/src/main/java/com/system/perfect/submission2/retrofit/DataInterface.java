package com.system.perfect.submission2.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataInterface {
    @GET("movie/upcoming")
    Call<RequestResponse> getUpcoming(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<RequestResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
