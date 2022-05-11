package com.example.cure.ui.dashboard;

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




import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private MainRecipeAdapter mra;



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
}