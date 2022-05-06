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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.other.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private List<MainRecipeItem> items = new ArrayList<>();

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("JUST TEST THE API");


    }

    public LiveData<String> getText() {
        return mText;
    }


    public List<Recipe> getSortedRecipesByTimeAscOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByTimeAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByTimeDesOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByTimeDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByCaloriesAscOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByCaloriesAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByCaloriesDesOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByCaloriesDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByFatAscOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByFatAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByFatDesOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByFatDescendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByProteinAscOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByProteinAscendingOrder(recipes);
    }

    public List<Recipe> getSortedRecipesByProteinDesOrd(List<Recipe> recipes){
        return Sorting.sortRecipesByProteinDescendingOrder(recipes);
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
        APIConnection.getRootModel("Chicken", new OnResponseListener() {
            @Override
            public void recipeByIdFetched(SpecificRecipeRoot sr) {

            }

            @Override
            public void recipesByQueryFetched(Root r) {

                for (Hit hit : r.getHits()) {
                    Recipe rec = hit.getRecipe();
                    items.add(new MainRecipeItem(rec.getLabel(), rec.getImage(), (int)rec.getCalories()+" kcal",
                            "Fat    "+(int)rec.getTotalNutrients().getFat().getQuantity() + " " + rec.getTotalNutrients().getFat().getUnit(), "Protein    "+(int)rec.getTotalNutrients().getProtein().getQuantity() +" "+ rec.getTotalNutrients().getProtein().getUnit(),
                            "Carbs    "+(int)rec.getTotalNutrients().getCarbs().getQuantity() + " " +rec.getTotalNutrients().getCarbs().getUnit(), (int)rec.getTotalTime() + " minuter"));
                }
            }
        });
    }

    public List<MainRecipeItem> getItems() {
        fetchData();
        return items;
    }


}