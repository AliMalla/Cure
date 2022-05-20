package com.example.cure.ui.my_recipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cure.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DailyRecipeAdapter extends BaseAdapter {

    private List<DailyRecipeItem> dailyRecipeItems;
    private Context context;

    public DailyRecipeAdapter(List<DailyRecipeItem> dailyRecipeItems, Context context) {
        this.dailyRecipeItems = dailyRecipeItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dailyRecipeItems.size();
    }

    @Override
    public Object getItem(int i) {
        return dailyRecipeItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (v == null) {
            v = mInflater.inflate(R.layout.listview_item, viewGroup, false);
        }


        TextView recipeName = (TextView) v.findViewById(R.id.recipeItemName);
        TextView recipeCalories = (TextView)v.findViewById(R.id.recipeItemCalories);
        TextView recipeType = (TextView)v.findViewById(R.id.mealType);
        ImageView image = (ImageView) v.findViewById(R.id.recipeImage);

        recipeName.setText(dailyRecipeItems.get(i).getName());
        recipeCalories.setText(dailyRecipeItems.get(i).getCalories() + " kcal");
        recipeType.setText(dailyRecipeItems.get(i).getType().toString());
        Picasso.get().load(dailyRecipeItems.get(i).getImage()).into(image);

        return v;
    }
}
