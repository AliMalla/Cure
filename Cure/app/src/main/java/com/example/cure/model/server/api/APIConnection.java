package com.example.cure.model.server.api;

import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is responsible for request and get response from API
 *
 * @author Ali Alkhaled
 */
public class APIConnection {

    private final static String BASE_URL = "https://api.edamam.com";
    private final static String APP_ID_1 = "6e5c599d";
    private final static String APP_KEY_1 = "477ece3e0fcc099b139f8064cb2594b9";
    //private final static String APP_ID_2 = "124b0fd4";
    //private final static String APP_KEY_2 = "3b9f97bbd888e5a892f65166028e9842";
    private final static String APP_ID_2 = "dacacfeb";
    private final static String APP_KEY_2 = "7e5dd972e4b3f51f1d92029b5895e4f4";
    private final static String APP_ID_3 = "89badfe2";
    private final static String APP_KEY_3 = "142b81372c145c2ce03795d647532cfd";
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


    private static Call<Root> makeRootConnection(String query) {
        PlaceHolderAPI placeHolderAPI = APIConnection.getRetrofit().create(PlaceHolderAPI.class);

        Call<Root> call = placeHolderAPI.getPosts(APIConnection.TYPE, query, APIConnection.APP_ID_1, APIConnection.APP_KEY_1);

        return call;

    }


    private static Call<SpecificRecipeRoot> makeRecipeByIdConnection(String id, int i) {
        PlaceHolderAPI placeHolderAPI = APIConnection.getRetrofit().create(PlaceHolderAPI.class);

        String ID = APP_ID_1;
        String KEY = APP_KEY_1;

        if (i%3==1) {
            ID = APP_ID_2;
            KEY = APP_KEY_2;
        }


        if (i%3==2) {
            ID = APP_ID_3;
            KEY = APP_KEY_3;
        }



        Call<SpecificRecipeRoot> call = placeHolderAPI.getRecipe(id, APIConnection.TYPE, ID, KEY);

        return call;
    }


    public static void getRootModel(String query, final OnResponseListener listener){
        Call<Root> call = APIConnection.makeRootConnection(query);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root r = response.body();
                    listener.recipesByQueryFetched(r);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    public static void getRecipeById(String id, int i, final OnResponseListener listener) {
        Call<SpecificRecipeRoot> call = APIConnection.makeRecipeByIdConnection(id, i);
        call.enqueue(new Callback<SpecificRecipeRoot>() {
            @Override
            public void onResponse(Call<SpecificRecipeRoot> call, Response<SpecificRecipeRoot> response) {
                if (response.isSuccessful()) {
                    SpecificRecipeRoot s = response.body();
                    listener.recipeByIdFetched(s);
                }
            }

            @Override
            public void onFailure(Call<SpecificRecipeRoot> call, Throwable t) {

            }
        });

    }

}
