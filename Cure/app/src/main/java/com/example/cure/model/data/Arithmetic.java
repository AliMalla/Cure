package com.example.cure.model.data;

import java.util.List;

public class Arithmetic {

    public double calculateTotalCalories(List<Recipe> recipes){
        double totalCalories = 0;
        for (Recipe rec : recipes){
            totalCalories += rec.getCalories();
        }
        return totalCalories;
    }

    public double calculateTotalFat(List<Recipe> recipes){
        double totalFat = 0;
        for (Recipe rec : recipes){
            totalFat += rec.getTotalNutrients().getFat().getQuantity();
        }
        return totalFat;
    }

    public double calculateTotalCarbs(List<Recipe> recipes){
        double totalCarbs = 0;
        for (Recipe rec : recipes){
            totalCarbs += rec.getTotalNutrients().getCarbs().getQuantity();
        }
        return totalCarbs;
    }


    public double calculateTotalProtein(List<Recipe> recipes){
        double totalProtein = 0;
        for (Recipe rec : recipes){
            totalProtein += rec.getTotalNutrients().getProtein().getQuantity();
        }
        return totalProtein;
    }


}
