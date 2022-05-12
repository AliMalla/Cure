package com.example.cure.ui.dashboard.recipeInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cure.databinding.ActivityRecipeInformationBinding;

public class RecipeInformationActivity extends AppCompatActivity {

    private ActivityRecipeInformationBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();



        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
