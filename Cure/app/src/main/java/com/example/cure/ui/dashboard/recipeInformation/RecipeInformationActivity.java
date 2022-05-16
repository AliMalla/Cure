package com.example.cure.ui.dashboard.recipeInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cure.databinding.ActivityRecipeInformationBinding;
import com.example.cure.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class RecipeInformationActivity extends AppCompatActivity {

    private ActivityRecipeInformationBinding binding;
    private RecipeInformationViewModel viewModel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //May need to be modified
        viewModel = new RecipeInformationViewModel(this);

        intent = getIntent();
        String id = intent.getStringExtra("recipeId");
        String name = intent.getStringExtra("recipeName");
        String image = intent.getStringExtra("recipeImage");
        String time = intent.getStringExtra("recipeTime");
        String weight = intent.getStringExtra("recipeWeight");
        String calories = intent.getStringExtra("recipeCalories");

        String water = intent.getStringExtra("recipeWater");
        String protein = intent.getStringExtra("recipeProtein");
        String fat = intent.getStringExtra("recipeFat");
        String carbs = intent.getStringExtra("recipeCarbs");
        String sugar = intent.getStringExtra("recipeSugar");
        String iron = intent.getStringExtra("recipeIron");
        String zinc = intent.getStringExtra("recipeZinc");
        String calcium = intent.getStringExtra("recipeCalcium");
        String vitaminA = intent.getStringExtra("recipeVitaminA");
        String vitaminB16 = intent.getStringExtra("recipeVitaminB16");
        String vitaminC = intent.getStringExtra("recipeVitaminC");
        String vitaminD = intent.getStringExtra("recipeVitaminD");
        String vitaminE = intent.getStringExtra("recipeVitaminE");


        List<String> nutrients = new ArrayList<>();
        nutrients.add("Water:      " + water);
        nutrients.add("Protein:      " + protein);
        nutrients.add("Fat:      " + fat);
        nutrients.add("Carbs:      " + carbs);
        nutrients.add("Sugar:      " + sugar);
        nutrients.add("Iron:      " + iron);
        nutrients.add("Zinc:      " + zinc);
        nutrients.add("Calcium:      " + calcium);
        nutrients.add("Vitamin A:      " + vitaminA);
        nutrients.add("Vitamin B16:      " + vitaminB16);
        nutrients.add("Vitamin C:      " + vitaminC);
        nutrients.add("Vitamin D:      " + vitaminD);
        nutrients.add("Vitamin E:      " + vitaminE);


        binding.recpieNameInfoPage.setText(name);
        Picasso.get().load(image).resize(208, 120).into(binding.recipeImageInfoPage);
        binding.recipeTimeInfoPage.setText(time);
        binding.recipeWeightInfoPage.setText(weight);
        binding.caloriesInfoPage.setText(calories);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.preference_category, nutrients);
        binding.nutrientsListViewInfoPage.setAdapter(adapter);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.addMealInfoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    viewModel.addMeal(id, new GregorianCalendar());
                    Toast.makeText(getBaseContext(), "The meal has been added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {}
            }
        });

    }
}
