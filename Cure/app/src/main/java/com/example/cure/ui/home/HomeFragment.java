package com.example.cure.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentHomeBinding;
import com.example.cure.ui.dashboard.DashboardFragment;
import com.example.cure.ui.home.eatenRecipeInformation.EatenRecipeInformationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Intent intent;
    private HorizontalCalendar horizontalCalendar;
    private Calendar selectedDate = new GregorianCalendar();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);


        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        horizontalCalendar = new HorizontalCalendar.Builder(root, binding.calendarView.getId())
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.refresh();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar newDate, int position) {
                selectedDate = newDate;
                homeViewModel.updateDailyRecipes(selectedDate);
                updateValues();
            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });


        homeViewModel.init(getContext());
        binding.previousRecipesList.setAdapter(homeViewModel.getAdapter(selectedDate));

        setDailyTotalCalories();
        setDailyTotalCarbs();
        setDailyTotalFat();
        setDailyTotalProtein();
        setNumberOfMeals();
        setRemarkingWhenNoMeals();



        FloatingActionButton fab = binding.floatingActionButton;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new fragment and transaction
                DashboardFragment newFragment = new DashboardFragment();
                newFragment.getModel().setDate(horizontalCalendar.getSelectedDate().getTime());
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.nav_host_fragment_activity_main, newFragment);

                // Commit the transaction
                transaction.commit();
            }
        });


        intent = new Intent(root.getContext(), EatenRecipeInformationActivity.class);


        /*
        binding.switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.switch1.setText("Done");
            }
        });


         */
        binding.previousRecipesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                sendSelectedEatenRecipeInfoToActivity(i);
                startActivity(intent);



                homeViewModel.getAdapter(selectedDate).notifyDataSetChanged();
                String id = homeViewModel.dailyRecipeItems.get(i).getId();

                homeViewModel.deleteItem(id, selectedDate);
                updateValues();
            }
        });



        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateValues();
    }


    private void updateValues(){
        setNumberOfMeals();
        setRemarkingWhenNoMeals();
        CountDownTimer c = new CountDownTimer(6000, 1500) {
            @Override
            public void onTick(long l) {
                setDailyTotalCalories();
                setDailyTotalCarbs();
                setDailyTotalFat();
                setDailyTotalProtein();
            }

            @Override
            public void onFinish() {
                setDailyTotalCalories();
                setDailyTotalCarbs();
                setDailyTotalFat();
                setDailyTotalProtein();
            }

        }.start();

    }


    private void setDailyTotalProtein() {
       String text = (int)homeViewModel.getDailyProtein() + " g";
       binding.totalDailyProtein.setText(text);
    }

    private void setDailyTotalFat(){
       String text = (int)homeViewModel.getDailyFat() + " g";
       binding.totalDailyFat.setText(text);
    }

    private void setDailyTotalCarbs(){
       String text = (int)homeViewModel.getDailyCarbs() + " g";
       binding.totalDailyCarbs.setText(text);
    }

    private void setDailyTotalCalories(){
        String text = ""+(int)homeViewModel.getDailyCalories();
        binding.totalDailyCalories.setText(text + " kcal");
    }

    private void setNumberOfMeals(){
       final int number = homeViewModel.getEatenMealsNumber(selectedDate);

       if (number == 1)
           binding.numberOfDailyMeals.setText("(" + number + " meal)");
       else
           binding.numberOfDailyMeals.setText("(" + number + " meals)");
    }

    private void setRemarkingWhenNoMeals(){
        if (homeViewModel.getEatenMealsNumber(selectedDate) != 0)
            binding.noRecipesYetRemarking.setText("");

        else
            binding.noRecipesYetRemarking.setText("No meals have been added yet");
    }


    private void sendSelectedEatenRecipeInfoToActivity(int i){
        String calories = "" + homeViewModel.dailyRecipeItems.get(i).getCalories();
        String name = "" + homeViewModel.dailyRecipeItems.get(i).getName();
        String image = "" + homeViewModel.dailyRecipeItems.get(i).getImage();
        String fat = "" + homeViewModel.dailyRecipeItems.get(i).getFat();
        String carbs = "" + homeViewModel.dailyRecipeItems.get(i).getCarbs();
        String protein = "" + homeViewModel.dailyRecipeItems.get(i).getProtein();
        String id = homeViewModel.dailyRecipeItems.get(i).getId();
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH);
        int day = selectedDate.get(Calendar.DATE);



        String selectedRecipeDate = "" + selectedDate.get(Calendar.YEAR) + "-" + (selectedDate.get(Calendar.MONTH) + 1) +"-"
                + selectedDate.get(Calendar.DATE);


        intent.putExtra("calories",calories);
        intent.putExtra("name",name);
        intent.putExtra("image",image);
        intent.putExtra("fat",fat);
        intent.putExtra("carbs",carbs);
        intent.putExtra("protein",protein);
        intent.putExtra("date",selectedRecipeDate);
        intent.putExtra("id",id);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);


    }

}