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
import com.example.cure.model.server.api.APIConnection;
import com.example.cure.model.server.api.OnResponseListener;
import com.example.cure.model.server.database.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.cure.model.server.database.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class HomeViewModel extends ViewModel {
    private Repository rep;
    private Arithmetic arithmetic;
    public List<DailyRecipeItem> dailyRecipeItems = new ArrayList<>();
    private List<String> tempIds = new ArrayList<>();
    private List<Recipe> recipes = new ArrayList<>();
    private DailyRecipeAdapter adapter;


    public void init(Context context){
        arithmetic = new Arithmetic();
        rep = Repository.getInstance(context);
        adapter = new DailyRecipeAdapter(dailyRecipeItems, context);
    }


    public void addMeal(Date i) {
        Log.e("Datum", i.toString());
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


    protected List<String> recipeIdList(Calendar date){
        List<String> list = rep.getRecipes(date);
        return list;
    }

    private void fetchDailyRecipes(Calendar date) {
        List<String> recipeIdList = recipeIdList(date);
        for (String id : recipeIdList) {
            if (!tempIds.contains(id)) {
                APIConnection.getRecipeById(id, new OnResponseListener() {
                    @Override
                    public void recipeByIdFetched(SpecificRecipeRoot sr) {
                        if (dailyRecipeItems.size() != recipeIdList.size()) {
                            Recipe recipe = sr.getRecipe();
                            String[] dishTypes = recipe.getDishType();
                            dailyRecipeItems.add(new DailyRecipeItem(id, recipe.getLabel(),
                                    recipe.getImage(), (int) (recipe.getCalories() / recipe.getYield()), dishTypes[0].toUpperCase(Locale.ROOT), (int)(recipe.getTotalNutrients().getFat().getQuantity()/recipe.getYield())));
                            recipes.add(recipe);
                            adapter.notifyDataSetChanged();

                            tempIds.add(id);
                        }
                    }

                    @Override
                    public void recipesByQueryFetched(Root r) {

                    }
                });
            }

        }

    }


    public void updateDailyRecipes(Calendar date) {
        fetchDailyRecipes(date);
    }

    public DailyRecipeAdapter getAdapter(Calendar date) {
        fetchDailyRecipes(date);
        return adapter;
    }
}