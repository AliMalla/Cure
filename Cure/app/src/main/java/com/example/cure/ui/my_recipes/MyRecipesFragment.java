package com.example.cure.ui.my_recipes;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentMyRecipesBinding;
import com.example.cure.ui.recipesearch.RecipeSearchFragment;
import com.example.cure.ui.my_recipes.eatenRecipeInformation.EatenRecipeInformationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class MyRecipesFragment extends Fragment {

    private MyRecipesViewModel myRecipesViewModel;
    private FragmentMyRecipesBinding binding;
    private Intent intent;
    private HorizontalCalendar horizontalCalendar;
    private Calendar selectedDate = new GregorianCalendar();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
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
                myRecipesViewModel.updateDailyRecipes(selectedDate);
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


        myRecipesViewModel.init(getContext());
        binding.previousRecipesList.setAdapter(myRecipesViewModel.getAdapter(selectedDate));

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
                RecipeSearchFragment newFragment = new RecipeSearchFragment();
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

                myRecipesViewModel.getAdapter(selectedDate).notifyDataSetChanged();
                String id = myRecipesViewModel.dailyRecipeItems.get(i).getId();

                myRecipesViewModel.deleteItem(id, selectedDate);
                updateValues();
                Toast.makeText(getContext(),"The meal has been deleted", Toast.LENGTH_SHORT).show();
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
       String text = (int) myRecipesViewModel.getDailyProtein() + " g";
       binding.totalDailyProtein.setText(text);
    }

    private void setDailyTotalFat(){
       String text = (int) myRecipesViewModel.getDailyFat() + " g";
       binding.totalDailyFat.setText(text);
    }

    private void setDailyTotalCarbs(){
       String text = (int) myRecipesViewModel.getDailyCarbs() + " g";
       binding.totalDailyCarbs.setText(text);
    }

    private void setDailyTotalCalories(){
        String text = ""+(int) myRecipesViewModel.getDailyCalories();
        binding.totalDailyCalories.setText(text + " kcal");
    }

    private void setNumberOfMeals(){
       final int number = myRecipesViewModel.getEatenMealsNumber(selectedDate);

       if (number == 1)
           binding.numberOfDailyMeals.setText("(" + number + " meal)");
       else
           binding.numberOfDailyMeals.setText("(" + number + " meals)");
    }

    private void setRemarkingWhenNoMeals(){
        if (myRecipesViewModel.getEatenMealsNumber(selectedDate) != 0)
            binding.noRecipesYetRemarking.setText("");

        else
            binding.noRecipesYetRemarking.setText("No meals have been added yet");
    }


    private void sendSelectedEatenRecipeInfoToActivity(int i){
        String calories = "" + myRecipesViewModel.dailyRecipeItems.get(i).getCalories();
        String name = "" + myRecipesViewModel.dailyRecipeItems.get(i).getName();
        String image = "" + myRecipesViewModel.dailyRecipeItems.get(i).getImage();
        String fat = "" + myRecipesViewModel.dailyRecipeItems.get(i).getFat();
        String carbs = "" + myRecipesViewModel.dailyRecipeItems.get(i).getCarbs();
        String protein = "" + myRecipesViewModel.dailyRecipeItems.get(i).getProtein();
        String id = myRecipesViewModel.dailyRecipeItems.get(i).getId();
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