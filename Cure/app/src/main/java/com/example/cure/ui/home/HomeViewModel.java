package com.example.cure.ui.home;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;

import com.example.cure.model.other.Arithmetic;

import com.example.cure.model.server.api.APIConnection;
import com.example.cure.model.server.api.OnResponseListener;
import com.example.cure.model.server.database.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private Repository rep;
    private Arithmetic arithmetic;
    public List<DailyRecipeItem> dailyRecipeItems = new ArrayList<>();
    private List<DailyRecipeItem> tempRecipeItems = new ArrayList<>();
    private List<String> storedIds = new ArrayList<>();
    private DailyRecipeAdapter adapter;
    private static Calendar currentDate = new GregorianCalendar();


    public void init(Context context){
        arithmetic = new Arithmetic();
        rep = Repository.getInstance(context);
        adapter = new DailyRecipeAdapter(dailyRecipeItems, context);
    }


    public void deleteItem(String id, Calendar date) {
        rep.deleteRecipe(id, date);

        for (DailyRecipeItem item : dailyRecipeItems) {

            if (item.getId().equals(id)) {

                dailyRecipeItems.remove(item);
                adapter.notifyDataSetChanged();
            }
        }

        storedIds.remove(id);
    }

    public double getDailyCalories() {
        return arithmetic.calculateTotalCalories(dailyRecipeItems);
    }

    public double getDailyProtein() {
        return arithmetic.calculateTotalProtein(dailyRecipeItems);
    }

    public double getDailyCarbs() {
        return arithmetic.calculateTotalCarbs(dailyRecipeItems);
    }

    public double getDailyFat() {
        return arithmetic.calculateTotalFat(dailyRecipeItems);
    }


    public void updateDailyRecipes(Calendar date) {
        fetchDailyRecipes(date);
    }

    public DailyRecipeAdapter getAdapter(Calendar date) {
        fetchDailyRecipes(date);
        return adapter;
    }

    public int getEatenMealsNumber(Calendar date) {
        return rep.getRecipes(date).size();
    }

    private List<String> getRecipeIds(Calendar date) {
        List<String> ids = rep.getRecipes(date);
        return ids;
    }

    /**
     * Fetching and bringing data from either the temp list or API
     */
    private void fetchDailyRecipes(Calendar date) {
        if (currentDate.get(Calendar.DATE) != date.get(Calendar.DATE) ||
                currentDate.get(Calendar.MONTH) != date.get(Calendar.MONTH) ||
                currentDate.get(Calendar.YEAR) != date.get(Calendar.YEAR)) { //Checking if input parameter is not the same as current date

            currentDate = date; // update current date
            dailyRecipeItems.clear(); // clear list from items that were eaten on the old date
            adapter.notifyDataSetChanged(); //update adapter's list content
            storedIds.clear();
        }
        List<String> recipeIdList = getRecipeIds(currentDate);
        int switcher = 0; //API switcher
        for (String id : recipeIdList) {

            if (!storedIds.contains(id)) {

                if (getIndexFromTempList(id) != -1) {

                    int index = getIndexFromTempList(id);
                    dailyRecipeItems.add(tempRecipeItems.get(index)); //Adding the item to the main list that is connected to the adapter
                    adapter.notifyDataSetChanged(); //updating adapter

                }
                else {
                    fetchFromAPI(id, switcher, recipeIdList);
                    switcher++;
                }

                storedIds.add(id); //store the newly added item's id into the list in order to avoid re-adding the item

            }
        }
    }


    /**
     *  Helper method for fetching data from API if the data does not already exist in the helper list (tempRecipeItems)
     */
    private void fetchFromAPI(String id, int switcher, List<String> recipeIdList){
        APIConnection.getRecipeById(id, switcher, new OnResponseListener() {
            @Override
            public void recipeByIdFetched(SpecificRecipeRoot sr) {
                if (dailyRecipeItems.size() != recipeIdList.size()) {
                    Recipe recipe = sr.getRecipe();
                    String[] dishTypes = recipe.getDishType();

                    final String name = recipe.getLabel();
                    final String image = recipe.getImage();
                    final String type = dishTypes[0];
                    final int calories = recipe.getCalories();
                    final int protein = recipe.getProtein();
                    final int fat = recipe.getFat();
                    final int carbs = recipe.getCarbs();
                    final int yield = recipe.getYield();

                    DailyRecipeItem item = new DailyRecipeItem(id, name, image, type, calories/yield, protein/yield, fat/yield, carbs/yield, yield);

                    dailyRecipeItems.add(item); //Adding the item to the main list that is connected to the adapter
                    adapter.notifyDataSetChanged(); //updating adapter
                    storeItemIntoTempList(item);//tempRecipeItems.add(item); //Storing the item to the helper list (so we can reuse it) to avoid unnecessary requests from API

                }
            }

            @Override
            public void recipesByQueryFetched(Root r) {

            }
        });
    }

    private int getIndexFromTempList(String id) {

        for (int i = 0; i < tempRecipeItems.size(); i++) {

            if (tempRecipeItems.get(i).getId().equals(id))
                return i;
        }

        return -1;
    }

    private void storeItemIntoTempList(DailyRecipeItem item) {
        if (isNewItem(item))
            tempRecipeItems.add(item);
    }

    private boolean isNewItem(DailyRecipeItem item) {

        for (DailyRecipeItem itm : tempRecipeItems) {

            if(itm.getId().equals(item.getId()))
                return false;
        }

        return true;
    }
}