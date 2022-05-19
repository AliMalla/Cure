package com.example.cure.model.other;

import com.example.cure.model.data.Recipe;
import com.example.cure.ui.home.DailyRecipeItem;

import java.util.List;

public class Arithmetic {
    public double calculateTotalCalories(List<Recipe> recipes){
        double totalCalories = 0;
        for (Recipe rec : recipes){
            totalCalories += (rec.getCalories()/rec.getYield());
        }
        return totalCalories;
    }

    public double calculateTotalFat(List<Recipe> recipes){
        double totalFat = 0;
        for (Recipe rec : recipes){
            totalFat += (rec.getTotalNutrients().getFat().getQuantity()/rec.getYield());
        }
        return totalFat;
    }

    public double calculateTotalCarbs(List<Recipe> recipes){
        double totalCarbs = 0;
        for (Recipe rec : recipes){
            totalCarbs += (rec.getTotalNutrients().getCarbs().getQuantity()/rec.getYield());
        }
        return totalCarbs;
    }

    public double calculateTotalProtein(List<Recipe> recipes){
        double totalProtein = 0;
        for (Recipe rec : recipes){
            totalProtein += (rec.getTotalNutrients().getProtein().getQuantity()/rec.getYield());
        }
        return totalProtein;
    }
    // Same, but for dailyItemRecipe instead
    public double calculateDailyTotalCalories(List<DailyRecipeItem> recipes) {
        double totalCalories = 0;
        for (DailyRecipeItem rec : recipes){
            totalCalories += rec.getCalories();
        }
        return totalCalories;
    }

    public double calculateDailyTotalFat(List<DailyRecipeItem> recipes){
        double totalFat = 0;
        for (DailyRecipeItem rec : recipes){
            totalFat += (rec.getFat());
        }
        return totalFat;
    }

    public double calculateDailyTotalCarbs(List<DailyRecipeItem> recipes) {
        double totalCarbs = 0;
        for (DailyRecipeItem rec : recipes){
            totalCarbs += (rec.getCarbs());
        }
        return totalCarbs;
    }

    public double calculateDailyTotalProtein(List<DailyRecipeItem> recipes){
        double totalProtein = 0;
        for (DailyRecipeItem rec : recipes){
            totalProtein += (rec.getProtein());
        }
        return totalProtein;
    }
}
