package com.example.cure.model.other;

import com.example.cure.model.data.Recipe;

import java.util.List;

public class Arithmetic {

    public static double calculateTotalCalories(List<Recipe> recipes){
        double totalCalories = 0;
        for (Recipe rec : recipes){
            totalCalories += rec.getCalories();
        }
        return totalCalories;
    }

    public static double calculateTotalFat(List<Recipe> recipes){
        double totalFat = 0;
        for (Recipe rec : recipes){
            totalFat += rec.getTotalNutrients().getFat().getQuantity();
        }
        return totalFat;
    }

    public static double calculateTotalCarbs(List<Recipe> recipes){
        double totalCarbs = 0;
        for (Recipe rec : recipes){
            totalCarbs += rec.getTotalNutrients().getCarbs().getQuantity();
        }
        return totalCarbs;
    }


    public static double calculateTotalProtein(List<Recipe> recipes){
        double totalProtein = 0;
        for (Recipe rec : recipes){
            totalProtein += rec.getTotalNutrients().getProtein().getQuantity();
        }
        return totalProtein;
    }


}
