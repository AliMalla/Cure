package com.example.cure.ui.home.eatenRecipeInformation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cure.databinding.EatenRecipeInformationBinding;

public class EatenRecipeInformationActivity extends AppCompatActivity {

    private EatenRecipeInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = EatenRecipeInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}