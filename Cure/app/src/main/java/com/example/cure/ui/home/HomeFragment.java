package com.example.cure.ui.home;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.databinding.FragmentHomeBinding;
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
        /*
        DailyRecipeItem itm1 = new DailyRecipeItem("493uh4nr9r3jr", "Chicken", "fsdfa", 500, DailyRecipeItem.Type.LUNCH);
        DailyRecipeItem itm2 = new DailyRecipeItem("493uh4nr9r3jr", "Meet", "fsdfa", 700, DailyRecipeItem.Type.DINNER);
        DailyRecipeItem itm3 = new DailyRecipeItem("493uh4nr9r3jr", "Fish and rice", "fsdfa", 1020, DailyRecipeItem.Type.LUNCH);
        DailyRecipeItem itm4 = new DailyRecipeItem("493uh4nr9r3jr","Meet", "fsdfa", 700, DailyRecipeItem.Type.DINNER);
        DailyRecipeItem itm5 = new DailyRecipeItem("493uh4nr9r3jr","Fish and rice", "fsdfa", 1020, DailyRecipeItem.Type.LUNCH);

        dailyRecipeItems.add(itm1);
        dailyRecipeItems.add(itm2);
        dailyRecipeItems.add(itm3);
        dailyRecipeItems.add(itm4);
        dailyRecipeItems.add(itm5);

         */

        //getValue();


        DailyRecipeAdapter adapter = new DailyRecipeAdapter(homeViewModel.getDailyRecipeItems(new GregorianCalendar()), getContext());
        binding.previousRecipesList.setAdapter(adapter);

        return root;
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