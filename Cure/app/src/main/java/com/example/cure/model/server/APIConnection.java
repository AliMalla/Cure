package com.example.cure.model.server;

import com.example.cure.model.data.Root;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConnection {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://api.edamam.com";
    public static String APP_ID = "124b0fd4";
    public static String APP_KEY = "3b9f97bbd888e5a892f65166028e9842";
    public static String TYPE = "public";


    public static Retrofit getRetrofit(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }

    public static Call<Root> getRootModel(String query) {
        PlaceHolderAPI placeHolderAPI = APIConnection.getRetrofit().create(PlaceHolderAPI.class);

        Call<Root> call = placeHolderAPI.getPosts(APIConnection.TYPE, query, APIConnection.APP_ID, APIConnection.APP_KEY);

        return call;

    }



}
