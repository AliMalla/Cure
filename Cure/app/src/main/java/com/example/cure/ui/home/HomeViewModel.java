package com.example.cure.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;
import com.example.cure.model.other.Arithmetic;
import com.example.cure.model.other.DataConverter;
import com.example.cure.model.server.api.APIConnection;
import com.example.cure.model.server.api.OnResponseListener;
import com.example.cure.model.server.database.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeViewModel extends ViewModel {
    private Repository rep;
    private Arithmetic arithmetic;
    public List<DailyRecipeItem> dailyRecipeItems = new ArrayList<>();
    private List<String> storedIds = new ArrayList<>();
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

    public double getDailyCalories() {
        return arithmetic.calculateTotalCalories(recipes);
    }

    public double getDailyProtein() {
        return arithmetic.calculateTotalProtein(recipes);
    }

    public double getDailyCarbs() {
        return arithmetic.calculateTotalCarbs(recipes);
    }

    public double getDailyFat() {
        return arithmetic.calculateTotalFat(recipes);
    }


    protected List<String> recipeIdList(Calendar date) {
        List<String> ids = rep.getRecipes(date);
        return ids;
    }

    private void fetchDailyRecipes(Calendar date) {
        List<String> recipeIdList = recipeIdList(date);
        int i = 0;
        for (String id : recipeIdList) {
            if (!itemAlreadyAdded(id, date)) {
                APIConnection.getRecipeById(id, i, new OnResponseListener() {
                    @Override
                    public void recipeByIdFetched(SpecificRecipeRoot sr) {
                        if (dailyRecipeItems.size() != recipeIdList.size()) {
                            Recipe recipe = sr.getRecipe();
                            String[] dishTypes = recipe.getDishType();
                            dailyRecipeItems.add(new DailyRecipeItem(id, recipe.getLabel(),
                                    recipe.getImage(), (int) (recipe.getCalories() / recipe.getYield()), dishTypes[0].toUpperCase(Locale.ROOT), (int)(recipe.getTotalNutrients().getFat().getQuantity()/recipe.getYield()),
                                    (int)(recipe.getTotalNutrients().getCarbs().getQuantity()/recipe.getYield())));
                            recipes.add(recipe);
                            adapter.notifyDataSetChanged();

                            storeItem(id, date);
                        }
                    }

                    @Override
                    public void recipesByQueryFetched(Root r) {

                    }
                }); i++;
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

    /**
     * Checking if an item is already existing in order to avoid repeated items on the same date
     */
    private boolean itemAlreadyAdded(String id, Calendar date) {

        for (String storedId : storedIds) {

            String dateStr = storedId.substring(0, 8);
            String idStr = storedId.substring(9);

            if(id.contains(idStr) && DataConverter.dateToString(date).contains(dateStr))
                return true;
        }

        return false;
    }

    /**
     * Storing an item's id and date in storedIds
     */
    private void storeItem(String id, Calendar date) {
        StringBuilder result = new StringBuilder();
        String dateStr = DataConverter.dateToString(date);

        result.append(dateStr);
        result.append("-");
        result.append(id);

        storedIds.add(result.toString());
    }
}