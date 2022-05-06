package com.example.cure.ui.home;

import android.os.Bundle;
import android.util.Log;
import com.example.cure.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.cure.model.data.SpecificRecipeRoot;
import com.example.cure.model.server.api.OnResponseListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fab = binding.floatingActionButton;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.addMeal();
            }
        });

        //View view = inflater.inflate(R.layout.fragment_home, container, false);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(root, binding.calendarView.getId())
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {




            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

        setDailyTotalCalories();
        setDailyTotalCarbs();
        setDailyTotalFat();
        setDailyTotalProtein();



        DailyRecipeAdapter adapter = new DailyRecipeAdapter(homeViewModel.getDailyRecipeItems(new GregorianCalendar()), getContext());
        binding.previousRecipesList.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setDailyTotalProtein() {
       String text = ""+ (int)homeViewModel.getDailyProtein(new GregorianCalendar());
        binding.totalDailyProtein.setText(text);
    }

    private void setDailyTotalFat(){
       String text = ""+(int)homeViewModel.getDailyFat(new GregorianCalendar());
       binding.totalDailyFat.setText(text);
    }

    private void setDailyTotalCarbs(){
       String text = ""+(int)homeViewModel.getDailyCarbs(new GregorianCalendar());
       binding.totalDailyCarbs.setText(text);
    }

    private void setDailyTotalCalories(){
       String text = ""+(int)homeViewModel.getDailyCalories(new GregorianCalendar());
       binding.totalDailyCalories.setText(text + " kcal");
    }



}