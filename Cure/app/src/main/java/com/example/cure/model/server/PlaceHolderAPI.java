package com.example.cure.model.server;

import com.example.cure.model.data.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceHolderAPI {

    @GET("/api/recipes/v2")
    Call<Root> getPosts(@Query("type") String type,
                        @Query("q") String query,
                        @Query("app_id") String appId,
                        @Query("app_key") String appKey);
}
