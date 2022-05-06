package com.example.cure.ui.home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cure.model.data.Hit;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;
import com.example.cure.model.other.Arithmetic;
import com.example.cure.model.other.DataConverter;
import com.example.cure.model.server.database.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.cure.model.server.database.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Repository rep;
    private List<DailyRecipeItem> dailyRecipeItems = new ArrayList<>();
    private final Arithmetic arithmetic;
    private List<SpecificRecipeRoot> list = new ArrayList<>();
    private List<Recipe> recipes = new ArrayList<>(); //test
    private Context context;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        //rep = new Repository(context);
        //this.dailyRecipeItems = getItems(recipes);
        this.arithmetic = new Arithmetic();
    }

    public HomeViewModel(Context context) {
        this.rep = new Repository(context);
        this.arithmetic = new Arithmetic();
        this.context = context;

    }

    public List<SpecificRecipeRoot> getList() {
        return list;
    }


    public void addMeal() {
        Log.e("Button", "Knapp funkar");
    }

    public void deleteItem(String id, Calendar date) {
        rep.deleteRecipe(id, date);
    }

    public double getDailyCalories(Calendar date) {
        return arithmetic.calculateTotalCalories(recipes);
    }

    public double getDailyProtein(Calendar date) {
        return arithmetic.calculateTotalProtein(recipes);
    }

    public double getDailyCarbs(Calendar date) {
        return arithmetic.calculateTotalCarbs(recipes);
    }

    public double getDailyFat(Calendar date) {
        return arithmetic.calculateTotalFat(recipes);
    }

    public LiveData<String> getText() {
        return mText;
    }



    public List<String> recipeIdList(Calendar date){
        List<String> list = new ArrayList<>();
        list.add("e7e22b5f9afdae010472f2084a76fd6c");
        list.add("b66666d5c882ca199f43def8f1b8a03f");

        return list;
    }


    private void fetchDailyRecipes(Calendar date) {
        List<String> recipeIdList = recipeIdList(date);
        for (String id : recipeIdList) {
            APIConnection.getRecipeById(id, new OnResponseListener() {
                @Override
                public void recipeByIdFetched(SpecificRecipeRoot sr) {
                    if (dailyRecipeItems.size() != recipeIdList.size()) {
                        dailyRecipeItems.add(new DailyRecipeItem(id, sr.getRecipe().getLabel(),
                                sr.getRecipe().getImage(), (int) sr.getRecipe().getCalories(), DailyRecipeItem.Type.LUNCH));
                        recipes.add(sr.getRecipe());
                    }
                }

                @Override
                public void recipesByQueryFetched(Root r) {

                }
            });
        }

    }


    public List<DailyRecipeItem> getDailyRecipeItems(Calendar date) {
        fetchDailyRecipes(date);
        return dailyRecipeItems;
    }



}