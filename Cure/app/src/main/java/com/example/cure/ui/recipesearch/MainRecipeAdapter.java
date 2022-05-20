package com.example.cure.ui.recipesearch;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cure.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainRecipeAdapter extends ArrayAdapter {

    private List<MainRecipeItem> items;
    private Context context;

    public MainRecipeAdapter(List<MainRecipeItem> items, Context context) {
        super(context,R.layout.listview_recipe,items);
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
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
            v = mInflater.inflate(R.layout.listview_recipe, viewGroup, false);
        }


        TextView recipeName = (TextView) v.findViewById(R.id.recipeNameMain);
        TextView recipeCalories = (TextView)v.findViewById(R.id.recipeCaloriesMain);
        TextView recipeTime = (TextView)v.findViewById(R.id.recipeTimeMain);
        TextView recipeFat = (TextView)v.findViewById(R.id.recipeFatMain);
        TextView recipeProtein = (TextView)v.findViewById(R.id.recipeProteinMain);
        TextView recipeCarbs = (TextView)v.findViewById(R.id.recipeCarbsMain);
        TextView recipeServings = (TextView)v.findViewById(R.id.recipeTotalServingsMain);
        ImageView recipeImage = (ImageView) v.findViewById(R.id.recipeImageMain);

        recipeName.setText(items.get(i).getName());
        recipeCalories.setText(items.get(i).getCalories());
        recipeTime.setText(items.get(i).getTime());
        recipeFat.setText(items.get(i).getFat());
        recipeProtein.setText(items.get(i).getProtein());
        recipeCarbs.setText(items.get(i).getCarbs());
        recipeServings.setText(items.get(i).getYield());
        Picasso.get().load(items.get(i).getImage()).resize(300, 115).into(recipeImage);


        return v;
    }

}
