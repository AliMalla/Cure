package com.example.cure.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cure.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<Item> items;
    private Context context;

    public ListViewAdapter(List<Item> items, Context context) {
        //super(context, R.layout.listview_item, items);
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
            v = mInflater.inflate(R.layout.listview_item, viewGroup, false);
        }


        TextView recipeName = (TextView) v.findViewById(R.id.recipeItemName);
        TextView recipeCalories = (TextView)v.findViewById(R.id.recipeItemCalories);
        TextView recipeType = (TextView)v.findViewById(R.id.mealType);
        ImageView image = (ImageView) v.findViewById(R.id.recipeImage);

        recipeName.setText(items.get(i).getName());
        recipeCalories.setText(""+items.get(i).getCalories() + " kcal");
        recipeType.setText(items.get(i).getType().toString());

        ImageView imageView = (ImageView) v.findViewById(R.id.deleteRecipe);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //homeViewModel.deleteRecipe(,new GregorianCalendar(2022,04,29));
                Toast.makeText(context,"The recipe is deleted",Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
