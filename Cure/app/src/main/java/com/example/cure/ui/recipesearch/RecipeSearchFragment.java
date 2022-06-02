package com.example.cure.ui.recipesearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentRecipeSearchBinding;
import com.example.cure.ui.recipesearch.recipeInformation.RecipeInformationActivity;


import java.util.ArrayList;
import java.util.List;


public class RecipeSearchFragment extends Fragment {

    private static RecipeSearchViewModel recipeSearchViewModel;
    private FragmentRecipeSearchBinding binding;
    private MainRecipeAdapter mra;
    private Intent intent;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeSearchViewModel =
                new ViewModelProvider(this).get(RecipeSearchViewModel.class);

        binding = FragmentRecipeSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mra = new MainRecipeAdapter(new ArrayList<>(), root.getContext());
        recipeSearchViewModel.updateItems();
        binding.mainRecipeListView.setAdapter(mra);



        ///////// search recipe stuff
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recipeSearchViewModel.updateItemsBySearch(s);
                //binding.searchView.onActionViewExpanded();
                binding.searchView.clearFocus();
                binding.searchView.setQuery("", false);
               return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        /////////////////////////////


        recipeSearchViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<MainRecipeItem>>() {
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
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, recipeSearchViewModel.getSortTypes());
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spSort.setAdapter(arrayAdapter);

        binding.spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String sortType = adapterView.getItemAtPosition(i).toString();
                recipeSearchViewModel.sortRecipesBySpecificType(sortType,getContext());
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
                startActivity(intent.putExtras(recipeSearchViewModel.populateIntentDetails(i)));
            }
        });


        recipeSearchViewModel.getEmptySearchRecipeText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.noRecipesFound.setText(s);
            }
        });

        return root;
    }

    public RecipeSearchViewModel getModel(){
        return recipeSearchViewModel;
    }
}