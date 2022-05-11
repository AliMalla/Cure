package com.example.cure.ui.dashboard;


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
import java.util.List;

import com.example.cure.model.other.Sorting;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Sorting sorting = new Sorting();

    private List<MainRecipeItem> items = new ArrayList<>();

    private MutableLiveData<List<MainRecipeItem>> recipes = new MutableLiveData<>();

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


    public List<Recipe> getSortedRecipesByTimeAscOrd(List<Recipe> recipes){
        return sorting.sortRecipesByTimeAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByTimeDesOrd(List<Recipe> recipes){
        return sorting.sortRecipesByTimeDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByCaloriesAscOrd(List<Recipe> recipes){
        return sorting.sortRecipesByCaloriesAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByCaloriesDesOrd(List<Recipe> recipes){
        return sorting.sortRecipesByCaloriesDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByFatAscOrd(List<Recipe> recipes){
        return sorting.sortRecipesByFatAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByFatDesOrd(List<Recipe> recipes){
        return sorting.sortRecipesByFatDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByProteinAscOrd(List<Recipe> recipes){
        return sorting.sortRecipesByProteinAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByProteinDesOrd(List<Recipe> recipes){
        return sorting.sortRecipesByProteinDescendingOrder(recipes);
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


    private void fetchData(){
        APIConnection.getRootModel(recipeName, new OnResponseListener() {
            @Override
            public void recipeByIdFetched(SpecificRecipeRoot sr) {

            }

            @Override
            public void recipesByQueryFetched(Root r) {
                List<MainRecipeItem> items = new ArrayList<>();

                for (Hit hit : r.getHits()) {
                    Recipe rec = hit.getRecipe();
                    items.add(new MainRecipeItem(rec.getLabel(), rec.getImage(), (int)rec.getCalories()+" kcal",
                            "Fat    "+(int)rec.getTotalNutrients().getFat().getQuantity() + " " + rec.getTotalNutrients().getFat().getUnit(), "Protein    "+(int)rec.getTotalNutrients().getProtein().getQuantity() +" "+ rec.getTotalNutrients().getProtein().getUnit(),
                            "Carbs    "+(int)rec.getTotalNutrients().getCarbs().getQuantity() + " " +rec.getTotalNutrients().getCarbs().getUnit(), (int)rec.getTotalTime() + " minuter"));
                }
                recipes.postValue(items);
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
        return recipes;
    }

    public String getDefaultRecipeName() {
        return defaultRecipeName;
    }
    public void setDefaultRecipeName(String defaultRecipeName){this.defaultRecipeName = defaultRecipeName;}
}