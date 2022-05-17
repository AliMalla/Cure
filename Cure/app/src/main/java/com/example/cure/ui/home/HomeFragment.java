package com.example.cure.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentHomeBinding;
import com.example.cure.ui.dashboard.DashboardFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        homeViewModel.init(getContext());
        binding.previousRecipesList.setAdapter(homeViewModel.getAdapter(new GregorianCalendar()));

        setDailyTotalCalories();
        setDailyTotalCarbs();
        setDailyTotalFat();
        setDailyTotalProtein();
        setNumberOfMeals();

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
                homeViewModel.updateDailyRecipes(date);
            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

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



        return root;
    }



    @Override
    public void onResume() {
        super.onResume();
        updateValues();
    }

    private void updateValues(){
        if(binding.totalDailyCalories.getText().equals("0 kcal") && homeViewModel.recipeIdList(new GregorianCalendar()).size() > 0) {
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
    }


    private void setDailyTotalProtein() {
       String text = (int)homeViewModel.getDailyProtein(new GregorianCalendar()) + " g";
       binding.totalDailyProtein.setText(text);
    }

    private void setDailyTotalFat(){
       String text = (int)homeViewModel.getDailyFat(new GregorianCalendar()) + " g";
       binding.totalDailyFat.setText(text);
    }

    private void setDailyTotalCarbs(){
       String text = (int)homeViewModel.getDailyCarbs(new GregorianCalendar()) + " g";
       binding.totalDailyCarbs.setText(text);
    }

    private void setDailyTotalCalories(){
       String text = ""+(int)homeViewModel.getDailyCalories(new GregorianCalendar());
       binding.totalDailyCalories.setText(text + " kcal");
    }

    private void setNumberOfMeals(){
       final int number = homeViewModel.recipeIdList(new GregorianCalendar()).size();

       if (number == 1)
           binding.numberOfDailyMeals.setText("(" + number + " meal)");
       else
           binding.numberOfDailyMeals.setText("(" + number + " meals)");
    }



}