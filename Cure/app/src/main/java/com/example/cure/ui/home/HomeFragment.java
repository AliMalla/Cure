package com.example.cure.ui.home;

import android.os.Bundle;
import com.example.cure.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.R;
import com.example.cure.databinding.FragmentHomeBinding;

import java.util.Calendar;
import java.util.List;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
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




        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        setDailyTotalCalories();
        setDailyTotalCarbs();
        setDailyTotalFat();
        setDailyTotalProtein();

        List<Item> items = new ArrayList<>();
        Item itm1 = new Item("493uh4nr9r3jr", "Chicken", "fsdfa", 500, Item.Type.LUNCH);
        Item itm2 = new Item("493uh4nr9r3jr", "Meet", "fsdfa", 700, Item.Type.DINNER);
        Item itm3 = new Item("493uh4nr9r3jr", "Fish and rice", "fsdfa", 1020, Item.Type.LUNCH);
        Item itm4 = new Item("493uh4nr9r3jr","Meet", "fsdfa", 700, Item.Type.DINNER);
        Item itm5 = new Item("493uh4nr9r3jr","Fish and rice", "fsdfa", 1020, Item.Type.LUNCH);

        items.add(itm1);
        items.add(itm2);
        items.add(itm3);
        items.add(itm4);
        items.add(itm5);



        String[] test = {"java", "python", "javascript", "PHP", "C#", "C++"};
        //ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.listview_item, R.id.recipeItemName, test);
        ListViewAdapter adapter = new ListViewAdapter(items, getContext()
        );
        binding.previousRecipesList.setAdapter(adapter);
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setDailyTotalProtein(){
       // String text = ""+ (int)homeViewModel.getDailyProtein(new GregorianCalendar());
       // binding.totalDailyProtein.setText(text);
    }

    private void setDailyTotalFat(){
       // String text = ""+(int)homeViewModel.getDailyFat(new GregorianCalendar());
       // binding.totalDailyFat.setText(text);
    }

    private void setDailyTotalCarbs(){
       // String text = ""+(int)homeViewModel.getDailyCarbs(new GregorianCalendar());
       // binding.totalDailyCarbs.setText(text);
    }

    private void setDailyTotalCalories(){
       // String text = ""+(int)homeViewModel.getDailyCalories(new GregorianCalendar());
       // binding.totalDailyCalories.setText(text);
    }
}