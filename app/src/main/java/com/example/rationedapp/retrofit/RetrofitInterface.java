package com.example.rationedapp.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/api/recipes/v2")
    Call<SearchResponse> customSearch(@Query("app_key") String API_KEY,
                                      @Query("app_id") String SEARCH_ID_cx,
                                      @Query("q") String keyword);
}
