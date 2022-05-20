package com.example.cure.ui.recipesearch.recipeInformation;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.cure.model.server.database.Repository;

import java.util.Calendar;

/**
 *
 * @author Ali Alkhaled
 */
public class RecipeInformationViewModel extends ViewModel {

    private final Repository rep;

    public RecipeInformationViewModel(Context context) {
        this.rep = Repository.getInstance(context);
    }

    protected void addMeal(String recipeId, Calendar date) {
        rep.addRecipe(recipeId, date);
    }

    protected boolean checkMealAlreadyEaten(String recipeId, Calendar date) {
        return rep.getRecipes(date).contains(recipeId);
    }



}
