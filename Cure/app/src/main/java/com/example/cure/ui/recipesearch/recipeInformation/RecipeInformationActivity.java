package com.example.cure.ui.recipesearch.recipeInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.cure.databinding.ActivityRecipeInformationBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class RecipeInformationActivity extends AppCompatActivity {

    private ActivityRecipeInformationBinding binding;
    private RecipeInformationViewModel viewModel;
    private Intent intent;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //May need to be modified
        viewModel = new RecipeInformationViewModel(this);

        intent = getIntent();
        id = intent.getStringExtra("recipeId");



        setBasicInfo();
        setRecipePrimaryValues();


        ArrayList<String> ingredients = intent.getStringArrayListExtra("ingredients");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.preference_category, getNutrients() );
        binding.nutrientsListViewInfoPage.setAdapter(adapter);

        ArrayAdapter ingredientsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ingredients);
        binding.ingredients.setAdapter(ingredientsAdapter);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.bannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        onClickAddMeal();


    }

    private void setBasicInfo(){

        String name = intent.getStringExtra("recipeName");
        String image = intent.getStringExtra("recipeImage");

        binding.recpieNameInfoPage.setText(name);
        Picasso.get().load(image).resize(175, 120).into(binding.recipeImageInfoPage);
    }

    private List<String> getNutrients(){

        List<String> nutrients = new ArrayList<>();

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

        nutrients.add("Water:  " + water);
        nutrients.add("Protein:  " + protein);
        nutrients.add("Fat:  " + fat);
        nutrients.add("Carbs:  " + carbs);
        nutrients.add("Sugar:  " + sugar);
        nutrients.add("Iron:  " + iron);
        nutrients.add("Zinc:  " + zinc);
        nutrients.add("Calcium:  " + calcium);
        nutrients.add("Vitamin A:  " + vitaminA);
        nutrients.add("Vitamin B16:  " + vitaminB16);
        nutrients.add("Vitamin C:  " + vitaminC);
        nutrients.add("Vitamin D:  " + vitaminD);
        nutrients.add("Vitamin E:  " + vitaminE);

        return nutrients;
    }

    private void setRecipePrimaryValues(){

        String time = intent.getStringExtra("recipeTime");
        String weight = intent.getStringExtra("recipeWeight");
        String calories = intent.getStringExtra("recipeCalories");
        int yield = intent.getIntExtra("recipeYield", 0);

        binding.recipeTimeInfoPage.setText(time);
        binding.recipeWeightInfoPage.setText(weight);
        binding.caloriesInfoPage.setText(calories);


        if(yield > 1)
            binding.remarkingInfoPage.setText("This meal is enough for " + yield + " people");
        else
            binding.remarkingInfoPage.setText("This meal is enough for " + yield + " person");



    }

    public void onClickAddMeal(){
        binding.addMealInfoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if(viewModel.checkMealAlreadyEaten(id, new GregorianCalendar())) {
                        Toast.makeText(getBaseContext(), "This meal has already been eaten on the chosen date", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        viewModel.addMeal(id, new GregorianCalendar());
                        Toast.makeText(getBaseContext(), "The meal has been added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception e) {}
            }
        });
    }
}
