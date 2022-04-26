package com.example.cure.model.server;

import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConnection {

    private final static String BASE_URL = "https://api.edamam.com";
    private final static String APP_ID = "124b0fd4";
    private final static String APP_KEY = "3b9f97bbd888e5a892f65166028e9842";
    private final static String TYPE = "public";
    private static Retrofit retrofit;


    private static Retrofit getRetrofit(){
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


    public static Call<SpecificRecipeRoot> getRecipeById(String id) {
        PlaceHolderAPI placeHolderAPI = APIConnection.getRetrofit().create(PlaceHolderAPI.class);

        Call<SpecificRecipeRoot> call = placeHolderAPI.getRecipe(id, APIConnection.TYPE, APIConnection.APP_ID, APIConnection.APP_KEY);

        return call;
    }





}
