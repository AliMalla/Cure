package com.example.cure.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.databinding.FragmentDashboardBinding;
import com.example.cure.model.data.Hit;
import com.example.cure.model.data.Image;
import com.example.cure.model.data.Links;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.server.APIConnection;
import com.example.cure.model.data.Root;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

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

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //For testing
        binding.testButton.setOnClickListener(new View.OnClickListener() {
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


                  /*APIConnection.getRecipeById("672c9e7e3fbc6240477d99152ba8f6b3").enqueue(new Callback<Recipe>() {
                    @Override
                    public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                        if (response.isSuccessful()) {

                            Recipe recipe = response.body();

                            String name = recipe.getLabel();
                            int cal = (int)recipe.getCalories();
                            Log.e("DashboardFragment", "onResponse: code : " + response.code() + "\nName: " + name + "\nCalories: " + cal);

                        }
                    }

                    @Override
                    public void onFailure(Call<Recipe> call, Throwable t) {

                    }
                });
                */
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}