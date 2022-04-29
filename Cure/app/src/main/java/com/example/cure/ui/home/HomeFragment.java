package com.example.cure.ui.home;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
}