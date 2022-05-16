package com.example.cure.ui.dashboard.recipeInformation;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.cure.model.server.database.Repository;

import java.util.Calendar;

public class RecipeInformationViewModel extends ViewModel {

    private final Repository rep;

    public RecipeInformationViewModel(Context context) {
        this.rep = Repository.getInstance(context);
    }

    public void addMeal(String recipeId, Calendar date) {
        rep.addRecipe(recipeId, date);
    }
}
