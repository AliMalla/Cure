package com.example.cure.ui.dashboard;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
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
import java.util.Date;
import java.util.List;


public class DashboardFragment extends Fragment {

    private static DashboardViewModel dashboardViewModel;
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
                binding.searchView.clearFocus();
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
                if(recipeItems.isEmpty()){
                    binding.noRecipesFound.setVisibility(View.VISIBLE);
                }
                else {
                    binding.noRecipesFound.setVisibility(View.INVISIBLE);

                }
            }
        });


        ///////// Sorting stuff
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, dashboardViewModel.getSortTypes());
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spSort.setAdapter(arrayAdapter);

        binding.spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String sortType = adapterView.getItemAtPosition(i).toString();
                dashboardViewModel.sortRecipesBySpecificType(sortType,getContext());
                adapterView.setSelection(0);
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
                startActivity(intent.putExtras(dashboardViewModel.populateIntentDetails(i)));
            }
        });


        dashboardViewModel.getEmptySearchRecipeText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.noRecipesFound.setText(s);
            }
        });



        return root;
    }

    public DashboardViewModel getModel(){
        return dashboardViewModel;
    }



}