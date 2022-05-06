package com.example.cure.ui.dashboard;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentDashboardBinding;

import com.example.cure.model.data.Hit;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.Root;
import com.example.cure.model.server.api.APIConnection;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ///////// search recipe stuff
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getRecipeBySearchFunction(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        /////////////////////////////





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


        return root;
    }


    private void getRecipeBySearchFunction(String s){
        APIConnection.getRootModel(s).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(response.isSuccessful() && response != null){
                    Log.e("DashboardFragment", "onResponse: code :" + response.code());
                    Hit[] hits = response.body().getHits();
                    for (Hit hit : hits) {
                        final String label = hit.getRecipe().getLabel();
                        final String image = hit.getRecipe().getImage();
                        final String protein = ""+hit.getRecipe().getTotalNutrients().getProtein();
                        final String calo = ""+hit.getRecipe().getCalories();
                        final String fat = ""+hit.getRecipe().getTotalNutrients().getFat();
                        final String carb = ""+hit.getRecipe().getTotalNutrients().getCarbs();
                        final String time = ""+hit.getRecipe().getTotalTime();
                        Log.e("DashboardFragment", "onResponse: recipe name :" + label +
                                "\n" + "recipe image: "
                                + image + "\n" + "recipe protein: " + protein + "\n" + "\n"+ "recipe time: "+ time+ "\n"+ "recipe calories: "+ calo + "\n\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e("DashboardFragment", "onFailure:" + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


//For testing
       /* binding.testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIConnection.getRootModel("Fish").enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {

                        if (response.isSuccessful() && response != null) {
                            Log.e("DashboardFragment", "onResponse: code :" + response.code());
                            Hit[] hits = response.body().getHits();


                            final String recipeName = hits[0].getRecipe().getLabel();
                            final int weight1 = (int)hits[0].getRecipe().getTotalWeight();
                            final int calories = (int)hits[0].getRecipe().getCalories();


                            binding.recipeName.setText(recipeName);
                            binding.recipeWeight.setText(""+weight1);
                            binding.recipeCallories.setText(""+calories);
                            String url = hits[0].getRecipe().getImage();
                            Picasso.get().load(url).into(binding.testImage);


                            for (Hit hit : hits) {

                                final String label = hit.getRecipe().getLabel();
                                final String weight = ""+hit.getRecipe().getTotalWeight();
                                final String image = hit.getRecipe().getImage();
                                final String yield = ""+hit.getRecipe().getYield();
                                final String calo = ""+hit.getRecipe().getCalories();




                               Log.e("DashboardFragment", "onResponse: recipe name :" + label +
                                        "\n" + "recipe weight: " + weight + "\n" + "recipe image: "
                                        + image + "\n" + "recipe yield: " + yield + "\n" + "recipe calories: "+ calo + "\n\n");
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {

                        Log.e("DashboardFragment", "onFailure:" + t.getMessage());

                    }
                });



                  APIConnection.getRecipeById("672c9e7e3fbc6240477d99152ba8f6b3").enqueue(new Callback<SpecificRecipeRoot>() {
                    @Override
                    public void onResponse(Call<SpecificRecipeRoot> call, Response<SpecificRecipeRoot> response) {
                        if (response.isSuccessful() && response != null) {

                            Recipe recipe = response.body().getRecipe();

                            String name = recipe.getLabel();
                            int cal = (int)recipe.getCalories();
                            Log.e("DashboardFragment", "onResponse: code : " + response.code() + "\nName: " + name + "\nCalories: " + cal);

                        }
                    }

                    @Override
                    public void onFailure(Call<SpecificRecipeRoot> call, Throwable t) {
                        Log.e("DashboardFragment", "onFailure:" + t.getMessage());

                    }
                });

            }
        });
        */