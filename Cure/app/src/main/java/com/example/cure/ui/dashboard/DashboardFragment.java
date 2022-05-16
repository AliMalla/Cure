package com.example.cure.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentDashboardBinding;
import com.example.cure.model.data.Ingredient;
import com.example.cure.model.data.Recipe;
import com.example.cure.ui.dashboard.recipeInformation.RecipeInformationActivity;


import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private MainRecipeAdapter mra;
    private Intent intent;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mra = new MainRecipeAdapter(new ArrayList<>(), root.getContext());
        dashboardViewModel.updateItems();
        binding.mainRecipeListView.setAdapter(mra);



        ///////// search recipe stuff
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                dashboardViewModel.updateItemsBySearch(s);
                binding.searchView.onActionViewExpanded();
               return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        /////////////////////////////


        dashboardViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<MainRecipeItem>>() {
            @Override
            public void onChanged(List<MainRecipeItem> recipeItems) {
                mra.clear();
                mra.addAll(recipeItems);
            }
        });

        dashboardViewModel.getLiveRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {

            }
        });

        ///////// Sorting stuff
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, dashboardViewModel.getSortTypes());
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spSort.setAdapter(arrayAdapter);

        binding.spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sortType= adapterView.getItemAtPosition(i).toString();

                switch (sortType){
                    case "Default": break;
                    case "Time+": Toast.makeText(getContext(),"Time+",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByTimeAscOrd(new ArrayList<>()); break;
                    case "Time-": Toast.makeText(getContext(),"Time-",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByCaloriesDesOrd(new ArrayList<>()); break;
                    case "Fat+": Toast.makeText(getContext(),"Fat+",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByFatAscOrd(new ArrayList<>()); break;
                    case "Fat-": Toast.makeText(getContext(),"Fat-",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByFatDesOrd(new ArrayList<>()); break;
                    case "Protein+": Toast.makeText(getContext(),"Protein+",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByProteinAscOrd(recipes); break;
                    case "Protein-": Toast.makeText(getContext(),"Protein-",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByProteinDesOrd(new ArrayList<>()); break;
                    case "Calories+": Toast.makeText(getContext(),"Calories+",Toast.LENGTH_LONG).show(); break;
                        //dashboardViewModel.getSortedRecipesByCaloriesAscOrd(recipes); break;
                    case "Calories-": Toast.makeText(getContext(),"Calories-",Toast.LENGTH_LONG).show(); break;
                    //dashboardViewModel.getSortedRecipesByCaloriesDesOrd(new ArrayList<>()); break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ////////////////////////////////////////




        intent = new Intent(root.getContext(), RecipeInformationActivity.class);
        binding.mainRecipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String recipeId = dashboardViewModel.getItems().getValue().get(i).getId();
                int recipeYield = (int)dashboardViewModel.getLiveRecipes().getValue().get(i).getYield();
                String recipeName = dashboardViewModel.getItems().getValue().get(i).getName();
                String recipeImage = dashboardViewModel.getItems().getValue().get(i).getImage();
                String recipeTime = (int)dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalTime() + " minutes";
                String recipeWeight = (int)dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalWeight() + " g";
                String recipeCalories = dashboardViewModel.getItems().getValue().get(i).getCalories();
                String recipeWater = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getWater().getValue();
                String recipeProtein = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getProtein().getValue();
                String recipeFat = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getFat().getValue();
                String recipeCarbs = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getCarbs().getValue();
                String recipeSugar = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getSugar().getValue();
                String recipeIron = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getIron().getValue();
                String recipeZinc = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getZinc().getValue();
                String recipeCalcium = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getCalcium().getValue();
                String recipeVitaminA = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminA().getValue();
                String recipeVitaminB16 = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminB6().getValue();
                String recipeVitaminC = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminC().getValue();
                String recipeVitaminD = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminD().getValue();
                String recipeVitaminE = dashboardViewModel.getLiveRecipes().getValue().get(i).getTotalNutrients().getVitaminE().getValue();

                Ingredient[] ingredients = dashboardViewModel.getLiveRecipes().getValue().get(i).getIngredients();
                ArrayList<String> ingredientsArrayList = new ArrayList<>();
                for(int j = 0; j < ingredients.length; j++){
                    String recipeIngredient = ingredients[j].getText() + ", weight: " + ingredients[j].getWeight();
                    ingredientsArrayList.add(recipeIngredient);
                }

                intent.putExtra("recipeId", recipeId);
                intent.putExtra("recipeYield", recipeYield);
                intent.putExtra("recipeName", recipeName);
                intent.putExtra("recipeImage", recipeImage);
                intent.putExtra("recipeTime", recipeTime);
                intent.putExtra("recipeWeight", recipeWeight);
                intent.putExtra("recipeCalories", recipeCalories);
                intent.putExtra("recipeWater", recipeWater);
                intent.putExtra("recipeProtein", recipeProtein);
                intent.putExtra("recipeFat", recipeFat);
                intent.putExtra("recipeCarbs", recipeCarbs);
                intent.putExtra("recipeSugar", recipeSugar);
                intent.putExtra("recipeIron", recipeIron);
                intent.putExtra("recipeZinc", recipeZinc);
                intent.putExtra("recipeCalcium", recipeCalcium);
                intent.putExtra("recipeVitaminA", recipeVitaminA);
                intent.putExtra("recipeVitaminB16", recipeVitaminB16);
                intent.putExtra("recipeVitaminC", recipeVitaminC);
                intent.putExtra("recipeVitaminD", recipeVitaminD);
                intent.putExtra("recipeVitaminE", recipeVitaminE);
                intent.putStringArrayListExtra("ingredients",ingredientsArrayList);

                startActivity(intent);


            }
        });

        return root;
    }
}