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

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public MutableLiveData<List<Root>> roots = new MutableLiveData<>();

    private List<MainRecipeItem> items = new ArrayList<>();

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("JUST TEST THE API");
    }

    public LiveData<String> getText() {
        return mText;
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