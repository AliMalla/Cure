package com.example.cure.ui.home;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.SpecificRecipeRoot;
import com.example.cure.model.other.Arithmetic;
import com.example.cure.model.other.DataConverter;
import com.example.cure.model.server.api.APIConnection;
import com.example.cure.model.server.database.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Repository rep;
    private List<Item> items;
    private final Arithmetic arithmetic;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        //rep = new Repository(context);
        //this.items = getItems(recipes);
        this.arithmetic = new Arithmetic();
    }

    public HomeViewModel(Context context) {
        this.rep = new Repository(context);
        this.arithmetic = new Arithmetic();
    }
    /*
    private List<Item> getItems(List<Recipe> recipes){
        final List<Item> items = new ArrayList<>();

        for (Recipe recipe : recipes) {
            String name = recipe.getLabel();
            String image = recipe.getImage();
            int calories = (int)recipe.getCalories();
            Item item = new Item(name, image, calories, Item.Type.LUNCH);
            items.add(item);
        }

        return items;
    }

 */

    public void deleteItem(String id, Calendar date) {
        rep.deleteRecipe(id, date);
    }

    public double getDailyCalories(Calendar date) {
        List<Recipe> recipes = rep.getRecipes(date);
        return arithmetic.calculateTotalCalories(recipes);
    }

    public double getDailyProtein(Calendar date) {
        List<Recipe> recipes = rep.getRecipes(date);
        return arithmetic.calculateTotalProtein(recipes);
    }

    public double getDailyCarbs(Calendar date) {
        List<Recipe> recipes = rep.getRecipes(date);
        return arithmetic.calculateTotalCarbs(recipes);
    }

    public double getDailyFat(Calendar date) {
        List<Recipe> recipes = rep.getRecipes(date);
        return arithmetic.calculateTotalFat(recipes);
    }

    public LiveData<String> getText() {
        return mText;
    }


}