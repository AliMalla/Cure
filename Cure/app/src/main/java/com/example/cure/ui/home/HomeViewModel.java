package com.example.cure.ui.home;

import android.content.Context;

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
import java.util.List;
import java.util.Locale;

public class HomeViewModel extends ViewModel {
    private Repository rep;
    private Arithmetic arithmetic;
    public List<DailyRecipeItem> dailyRecipeItems = new ArrayList<>();
    private List<String> storedIds = new ArrayList<>();
    private List<Recipe> recipes = new ArrayList<>();
    private DailyRecipeAdapter adapter;
    private Calendar date;

    public void init(Context context){
        arithmetic = new Arithmetic();
        rep = Repository.getInstance(context);
        adapter = new DailyRecipeAdapter(dailyRecipeItems, context);
    }

    public void deleteItem(String id, Calendar date) {
        rep.deleteRecipe(id, date);
    }

    public double getDailyCalories() { return arithmetic.calculateDailyTotalCalories(dailyRecipeItems); }

    public double getDailyProtein() { return arithmetic.calculateDailyTotalProtein(dailyRecipeItems); }

    public double getDailyCarbs() { return arithmetic.calculateDailyTotalCarbs(dailyRecipeItems); }

    public double getDailyFat() { return arithmetic.calculateDailyTotalFat(dailyRecipeItems); }

    protected List<String> recipeIdList(Calendar date) {
        List<String> ids = rep.getRecipes(date);
        return ids;
    }

    void clearList() {
        dailyRecipeItems.clear();
    }

    protected List<String> getRecipeIdList(Calendar date) {
        List<String> ids = rep.getRecipes(date);
        return ids;
    }

    private boolean fetchDailyRecipes(Calendar date) {
        List<String> recipeIdList = getRecipeIdList(date);
        int i = 0;
        for (String id : recipeIdList) {
            if (!itemAlreadyAdded(id, date)) {
                APIConnection.getRecipeById(id, i, new OnResponseListener() {
                    @Override
                    public void recipeByIdFetched(SpecificRecipeRoot sr) {
                        if (dailyRecipeItems.size() != recipeIdList.size()) {
                            Recipe recipe = sr.getRecipe();
                            String[] dishTypes = recipe.getDishType();
                            DailyRecipeItem dRI = new DailyRecipeItem(id,
                                    recipe.getLabel(),
                                    recipe.getImage(),
                                    (int) (recipe.getCalories() / recipe.getYield()),
                                    (int) (recipe.getTotalNutrients().getCarbs().getQuantity() / recipe.getYield()),
                                    (int) (recipe.getTotalNutrients().getFat().getQuantity() / recipe.getYield()),
                                    (int) (recipe.getTotalNutrients().getProtein().getQuantity() / recipe.getYield()),
                                    dishTypes[0].toUpperCase(Locale.ROOT));
                            dailyRecipeItems.add(dRI);
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
            else return false;
            //TODO Add code so that previously seen dailyRecipes are added to a list for accessing later
        }
        return true;
    }


    public boolean updateDailyRecipes(Calendar date) {
        return fetchDailyRecipes(date);
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