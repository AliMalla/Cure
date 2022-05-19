package com.example.cure.model.other;

import com.example.cure.model.data.IRecipe;

import java.util.List;

public class Arithmetic {

    public double calculateTotalCalories(List<? extends IRecipe> recipes){
        double totalCalories = 0;
        for (IRecipe rec : recipes){
            totalCalories += rec.getCalories();
        }
        return totalCalories;
    }

    public double calculateTotalFat(List<? extends IRecipe> recipes){
        double totalFat = 0;
        for (IRecipe rec : recipes){
            totalFat += rec.getFat();
        }
        return totalFat;
    }

    public double calculateTotalCarbs(List<? extends IRecipe> recipes){
        double totalCarbs = 0;
        for (IRecipe rec : recipes){
            totalCarbs += rec.getCarbs();
        }
        return totalCarbs;
    }


    public double calculateTotalProtein(List<? extends IRecipe> recipes){
        double totalProtein = 0;
        for (IRecipe rec : recipes){
            totalProtein += rec.getProtein();
        }
        return totalProtein;
    }


}
