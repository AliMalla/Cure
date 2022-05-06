package com.example.cure.model.other;

import com.example.cure.model.data.Recipe;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static List<Recipe> sortRecipesByTimeAscendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Double.compare(recipe.getTotalTime(), t1.getTotalTime());
            }
        });
        return recipes;
    }

    public static List<Recipe> sortRecipesByTimeDescendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Double.compare(t1.getTotalTime(), recipe.getTotalTime());
            }
        });
        return recipes;
    }

    public static List<Recipe> sortRecipesByCaloriesAscendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(recipe.getCalories(),t1.getCalories());
            }
        });
        return recipes;
    }

    public static List<Recipe> sortRecipesByCaloriesDescendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(t1.getCalories(), recipe.getCalories());
            }
        });
        return recipes;
    }


    public static List<Recipe> sortRecipesByFatAscendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(recipe.getTotalNutrients().getFat().getQuantity(),t1.getTotalNutrients().getFat().getQuantity());
            }
        });
        return recipes;
    }


    public static List<Recipe> sortRecipesByFatDescendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(t1.getTotalNutrients().getFat().getQuantity(), recipe.getTotalNutrients().getFat().getQuantity());
            }
        });
        return recipes;
    }



    public static List<Recipe> sortRecipesByCarbsAscendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(recipe.getTotalNutrients().getCarbs().getQuantity(),t1.getTotalNutrients().getCarbs().getQuantity());
            }
        });
        return recipes;
    }


    public static List<Recipe> sortRecipesByCarbsDescendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(t1.getTotalNutrients().getCarbs().getQuantity(), recipe.getTotalNutrients().getCarbs().getQuantity());
            }
        });
        return recipes;
    }



    public static List<Recipe> sortRecipesByProteinAscendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(recipe.getTotalNutrients().getProtein().getQuantity(),t1.getTotalNutrients().getProtein().getQuantity());
            }
        });
        return recipes;
    }


    public static List<Recipe> sortRecipesByProteinDescendingOrder(List<Recipe> recipes){
        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return Float.compare(t1.getTotalNutrients().getProtein().getQuantity(), recipe.getTotalNutrients().getProtein().getQuantity());
            }
        });
        return recipes;
    }
}
