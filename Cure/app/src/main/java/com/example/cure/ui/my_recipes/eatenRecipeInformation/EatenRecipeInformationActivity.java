package com.example.cure.ui.my_recipes.eatenRecipeInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cure.databinding.EatenRecipeInformationBinding;
import com.example.cure.ui.my_recipes.MyRecipesViewModel;
import com.squareup.picasso.Picasso;


public class EatenRecipeInformationActivity extends AppCompatActivity {

    private EatenRecipeInformationBinding binding;
    private MyRecipesViewModel myRecipesViewModel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesViewModel.class);

        binding = EatenRecipeInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myRecipesViewModel.init(binding.getRoot().getContext());

        intent = getIntent();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.eatenRecipeInformationName.setText(intent.getStringExtra("name"));
        binding.eatenRecipeInformationCalories.setText("Calories: "+ intent.getStringExtra("calories")+ " kcal");
        binding.eatenRecipeInformationFat.setText("Fat: "+ intent.getStringExtra("fat")+ " g");
        binding.eatenRecipeInformationCarbs.setText("Carbs: "+ intent.getStringExtra("carbs")+ " g");
        binding.eatenRecipeInformationProtein.setText("Protein: "+ intent.getStringExtra("protein")+ " g");
        String image = intent.getStringExtra("image");
        Picasso.get().load(image).resize(175, 120).into(binding.eatenRecipeInformationImageView);
        binding.eatenRecipeInformationDate.setText("Date: "+ intent.getStringExtra("date"));

    }
}
