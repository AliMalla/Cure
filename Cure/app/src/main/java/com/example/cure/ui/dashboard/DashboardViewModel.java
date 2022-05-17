package com.example.cure.ui.dashboard;


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
import com.example.cure.model.server.api.APIConnection;
import com.example.cure.model.server.api.OnResponseListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.cure.model.other.Sorting;

public class DashboardViewModel extends ViewModel {

    private Sorting sorting = new Sorting();

    private MutableLiveData<List<Recipe>> liveRecipes = new MutableLiveData<>();

    private MutableLiveData<List<MainRecipeItem>> mainItems = new MutableLiveData<>();

    private String recipeName;
    private String defaultRecipeName = "chicken";


    public DashboardViewModel() {
        recipeName = defaultRecipeName;
    }




    private void fetchData(){
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
                    items.add(new MainRecipeItem(getRecipeId(rec), rec.getLabel(), rec.getImage(), (int)rec.getCalories()+" kcal",
                            "Fat    "+(int)rec.getTotalNutrients().getFat().getQuantity() + " " + rec.getTotalNutrients().getFat().getUnit(), "Protein    "+(int)rec.getTotalNutrients().getProtein().getQuantity() +" "+ rec.getTotalNutrients().getProtein().getUnit(),
                            "Carbs    "+(int)rec.getTotalNutrients().getCarbs().getQuantity() + " " +rec.getTotalNutrients().getCarbs().getUnit(), (int)rec.getTotalTime() + " minutes"));
                    recipes.add(rec);
                }
                mainItems.postValue(items);
                liveRecipes.postValue(recipes);
            }
        });
    }

    public void updateItemsBySearch(String recipe_name) {
        if(!recipe_name.equals(recipeName)){
            recipeName = recipe_name;
        }
        fetchData();
    }

    public void updateItems(){
        recipeName = defaultRecipeName;
        fetchData();
    }

    public LiveData<List<MainRecipeItem>> getItems(){
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

    public void setDefaultRecipeName(String defaultRecipeName){this.defaultRecipeName = defaultRecipeName;}


    //////////////// SORTING STUFF /////////////////////////////////////
    private void sortMainRecipeItems(List<Recipe> sortedRecipes){
        List<Recipe> srtRecipes = sortedRecipes;
        List<MainRecipeItem> mainRecipeItemList = new ArrayList<>();
        mainItems.getValue().clear();
        for(Recipe rec : srtRecipes){
            mainRecipeItemList.add(new MainRecipeItem(getRecipeId(rec), rec.getLabel(), rec.getImage(), (int)rec.getCalories()+" kcal",
                    "Fat    "+(int)rec.getTotalNutrients().getFat().getQuantity() + " " + rec.getTotalNutrients().getFat().getUnit(), "Protein    "+(int)rec.getTotalNutrients().getProtein().getQuantity() +" "+ rec.getTotalNutrients().getProtein().getUnit(),
                    "Carbs    "+(int)rec.getTotalNutrients().getCarbs().getQuantity() + " " +rec.getTotalNutrients().getCarbs().getUnit(), (int)rec.getTotalTime() + " minuter"));
        }

        mainItems.postValue(mainRecipeItemList);
    }


    private void sortRecipesByTimeAscOrd(){
        sortMainRecipeItems(sorting.sortRecipesByTimeAscendingOrder(liveRecipes.getValue()));
    }

    private void sortRecipesByTimeDesOrd(){
        sortMainRecipeItems(sorting.sortRecipesByTimeDescendingOrder(liveRecipes.getValue()));
    }


    private void sortRecipesByCaloriesAscOrd(){
        sortMainRecipeItems(sorting.sortRecipesByCaloriesAscendingOrder(liveRecipes.getValue()));
    }

    private void sortRecipesByCaloriesDesOrd(){
        sortMainRecipeItems(sorting.sortRecipesByCaloriesDescendingOrder(liveRecipes.getValue()));
    }

    private void sortRecipesByFatAscOrd(){
        sortMainRecipeItems(sorting.sortRecipesByFatAscendingOrder(liveRecipes.getValue()));
    }

    private void sortRecipesByFatDesOrd(){
        sortMainRecipeItems(sorting.sortRecipesByFatDescendingOrder(liveRecipes.getValue()));
    }


    private void sortedRecipesByProteinAscOrd(){
        sortMainRecipeItems(sorting.sortRecipesByProteinAscendingOrder(liveRecipes.getValue()));
    }

    private void sortRecipesByProteinDesOrd(){
        sortMainRecipeItems(sorting.sortRecipesByProteinDescendingOrder(liveRecipes.getValue()));
    }




    public List<CharSequence> getSortTypes(){
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



    public void sortRecipesBySpecificType(String type, Context context){
        switch (type){
            case "Default": Toast.makeText(context,"SORTED RANDOMLY",Toast.LENGTH_LONG).show(); break;
            case "Time+": Toast.makeText(context,"SORTED BY TIME IN ASCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByTimeAscOrd(); break;
            case "Time-": Toast.makeText(context,"SORTED BY TIME IN DESCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByTimeDesOrd(); break;
            case "Fat+": Toast.makeText(context,"SORTED BY FAT IN ASCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByFatAscOrd(); break;
            case "Fat-": Toast.makeText(context,"SORTED BY FAT IN DESCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByFatDesOrd(); break;
            case "Protein+": Toast.makeText(context,"SORTED BY PROTEIN IN ASCENDING ORDER",Toast.LENGTH_LONG).show();
                sortedRecipesByProteinAscOrd(); break;
            case "Protein-": Toast.makeText(context,"SORTED BY PROTEIN IN DESCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByProteinDesOrd(); break;
            case "Calories+": Toast.makeText(context,"SORTED BY CALORIES IN ASCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByCaloriesAscOrd(); break;
            case "Calories-": Toast.makeText(context,"SORTED BY CALORIES IN DESCENDING ORDER",Toast.LENGTH_LONG).show();
                sortRecipesByCaloriesDesOrd(); break;
        }
    }
    ////////////////////////////////////////////////////////////////////


    private static Date date = new Date();
    public void setDate(Date time) {
        date = time;
        Log.e("date", date.toString());
    }
}