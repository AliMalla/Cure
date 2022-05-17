package com.example.cure.ui.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cure.model.data.Hit;
import com.example.cure.model.data.Ingredient;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.Root;
import com.example.cure.model.data.SpecificRecipeRoot;
import com.example.cure.model.server.api.APIConnection;
import com.example.cure.model.server.api.OnResponseListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.cure.model.other.Sorting;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Sorting sorting = new Sorting();

    private MutableLiveData<List<Recipe>> liveRecipes = new MutableLiveData<>();

    private MutableLiveData<List<MainRecipeItem>> mainItems = new MutableLiveData<>();

    private String recipeName;
    private String defaultRecipeName = "chicken";


    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("JUST TEST THE API");
        recipeName = defaultRecipeName;
    }

    public LiveData<String> getText() {
        return mText;
    }


    public List<Recipe> getSortedRecipesByTimeAscOrd(List<Recipe> recipes) {
        return sorting.sortRecipesByTimeAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByTimeDesOrd(List<Recipe> recipes) {
        return sorting.sortRecipesByTimeDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByCaloriesAscOrd(List<Recipe> recipes) {
        return sorting.sortRecipesByCaloriesAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByCaloriesDesOrd(List<Recipe> recipes) {
        return sorting.sortRecipesByCaloriesDescendingOrder(recipes);
    }


    public void sortRecipesByFatAscOrd() {
        sortMainRecipeItems(sorting.sortRecipesByFatAscendingOrder(liveRecipes.getValue()));
    }

    public void sortRecipesByFatDesOrd() {
        sortMainRecipeItems(sorting.sortRecipesByFatDescendingOrder(liveRecipes.getValue()));
    }


    public void sortedRecipesByProteinAscOrd() {
        sortMainRecipeItems(sorting.sortRecipesByProteinAscendingOrder(liveRecipes.getValue()));
    }

    public void sortRecipesByProteinDesOrd() {
        sortMainRecipeItems(sorting.sortRecipesByProteinDescendingOrder(liveRecipes.getValue()));
    }


    public List<CharSequence> getSortTypes() {
        ArrayList<CharSequence> sortTypes = new ArrayList<>();

        sortTypes.add("Default");
        sortTypes.add("Time+");
        sortTypes.add("Time-");
        sortTypes.add("Fat+");
        sortTypes.add("Fat-");
        sortTypes.add("Protein+");
        sortTypes.add("Protein-");
        sortTypes.add("Calories+");
        sortTypes.add("Calories-");


        return sortTypes;
    }


    private void fetchData() {
        APIConnection.getRootModel(recipeName, new OnResponseListener() {
            @Override
            public void recipeByIdFetched(SpecificRecipeRoot sr) {

            }

            @Override
            public void recipesByQueryFetched(Root r) {
                List<MainRecipeItem> items = new ArrayList<>();
                List<Recipe> recipes = new ArrayList<>();

                for (Hit hit : r.getHits()) {
                    Recipe rec = hit.getRecipe();
                    items.add(new MainRecipeItem(getRecipeId(rec), rec.getLabel(), rec.getImage(), (int) rec.getCalories() + " kcal",
                            "Fat    " + (int) rec.getTotalNutrients().getFat().getQuantity() + " " + rec.getTotalNutrients().getFat().getUnit(), "Protein    " + (int) rec.getTotalNutrients().getProtein().getQuantity() + " " + rec.getTotalNutrients().getProtein().getUnit(),
                            "Carbs    " + (int) rec.getTotalNutrients().getCarbs().getQuantity() + " " + rec.getTotalNutrients().getCarbs().getUnit(), (int) rec.getTotalTime() + " minutes"));
                    recipes.add(rec);
                }
                mainItems.postValue(items);
                liveRecipes.postValue(recipes);
            }
        });
    }

    public void updateItemsBySearch(String recipe_name) {
        if (!recipe_name.equals(recipeName)) {
            recipeName = recipe_name;
        }
        fetchData();
    }

    public void updateItems() {
        recipeName = defaultRecipeName;
        fetchData();
    }

    public LiveData<List<MainRecipeItem>> getItems() {
        return mainItems;
    }

    private String getRecipeId(Recipe recipe) {
        final String temp = recipe.getUri();
        final String id = temp.substring(temp.length() - 32);

        return id;
    }

    public MutableLiveData<List<Recipe>> getLiveRecipes() {
        return liveRecipes;
    }

    public String getDefaultRecipeName() {
        return defaultRecipeName;
    }

    public void setDefaultRecipeName(String defaultRecipeName) {
        this.defaultRecipeName = defaultRecipeName;
    }


    private void sortMainRecipeItems(List<Recipe> sortedRecipes) {
        List<Recipe> srtRecipes = sortedRecipes;
        List<MainRecipeItem> mainRecipeItemList = new ArrayList<>();
        mainItems.getValue().clear();
        for (Recipe rec : srtRecipes) {
            mainRecipeItemList.add(new MainRecipeItem(getRecipeId(rec), rec.getLabel(), rec.getImage(), (int) rec.getCalories() + " kcal",
                    "Fat    " + (int) rec.getTotalNutrients().getFat().getQuantity() + " " + rec.getTotalNutrients().getFat().getUnit(), "Protein    " + (int) rec.getTotalNutrients().getProtein().getQuantity() + " " + rec.getTotalNutrients().getProtein().getUnit(),
                    "Carbs    " + (int) rec.getTotalNutrients().getCarbs().getQuantity() + " " + rec.getTotalNutrients().getCarbs().getUnit(), (int) rec.getTotalTime() + " minuter"));
        }

        mainItems.postValue(mainRecipeItemList);
    }

    private static Date date = new Date();

    public void setDate(Date time) {
        date = time;
        Log.e("date", date.toString());
    }

    Bundle populateIntentDetails(int i) {
        String recipeId = getItems().getValue().get(i).getId();
        int recipeYield = (int) getLiveRecipes().getValue().get(i).getYield();
        String recipeName = getItems().getValue().get(i).getName();
        String recipeImage = getItems().getValue().get(i).getImage();
        String recipeTime = (int) getLiveRecipes().getValue().get(i).getTotalTime() + " minutes";
        String recipeWeight = (int) getLiveRecipes().getValue().get(i).getTotalWeight() + " g";
        String recipeCalories = getItems().getValue().get(i).getCalories();
        String recipeWater = getLiveRecipes().getValue().get(i).getTotalNutrients().getWater().getValue();
        String recipeProtein = getLiveRecipes().getValue().get(i).getTotalNutrients().getProtein().getValue();
        String recipeFat = getLiveRecipes().getValue().get(i).getTotalNutrients().getFat().getValue();
        String recipeCarbs = getLiveRecipes().getValue().get(i).getTotalNutrients().getCarbs().getValue();
        String recipeSugar = getLiveRecipes().getValue().get(i).getTotalNutrients().getSugar().getValue();
        String recipeIron = getLiveRecipes().getValue().get(i).getTotalNutrients().getIron().getValue();
        String recipeZinc = getLiveRecipes().getValue().get(i).getTotalNutrients().getZinc().getValue();
        String recipeCalcium = getLiveRecipes().getValue().get(i).getTotalNutrients().getCalcium().getValue();
        String recipeVitaminA = getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminA().getValue();
        String recipeVitaminB16 = getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminB6().getValue();
        String recipeVitaminC = getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminC().getValue();
        String recipeVitaminD = getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminD().getValue();
        String recipeVitaminE = getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminE().getValue();

        Ingredient[] ingredients = getLiveRecipes().getValue().get(i).getIngredients();
        ArrayList<String> ingredientsArrayList = new ArrayList<>();
        for (int j = 0; j < ingredients.length; j++) {
            String recipeIngredient = ingredients[j].getText() + ", weight: " + ingredients[j].getWeight();
            ingredientsArrayList.add(recipeIngredient);
        }

        Bundle bundle = new Bundle();
        bundle.putString("recipeId", recipeId);
        bundle.putString("date", date.toString());
        bundle.putInt("recipeYield", recipeYield);
        bundle.putString("recipeName", recipeName);
        bundle.putString("recipeImage", recipeImage);
        bundle.putString("recipeTime", recipeTime);
        bundle.putString("recipeWeight", recipeWeight);
        bundle.putString("recipeCalories", recipeCalories);
        bundle.putString("recipeWater", recipeWater);
        bundle.putString("recipeProtein", recipeProtein);
        bundle.putString("recipeFat", recipeFat);
        bundle.putString("recipeCarbs", recipeCarbs);
        bundle.putString("recipeSugar", recipeSugar);
        bundle.putString("recipeIron", recipeIron);
        bundle.putString("recipeZinc", recipeZinc);
        bundle.putString("recipeCalcium", recipeCalcium);
        bundle.putString("recipeVitaminA", recipeVitaminA);
        bundle.putString("recipeVitaminB16", recipeVitaminB16);
        bundle.putString("recipeVitaminC", recipeVitaminC);
        bundle.putString("recipeVitaminD", recipeVitaminD);
        bundle.putString("recipeVitaminE", recipeVitaminE);
        bundle.putStringArrayList("ingredients", ingredientsArrayList);


        return bundle;
    }

}