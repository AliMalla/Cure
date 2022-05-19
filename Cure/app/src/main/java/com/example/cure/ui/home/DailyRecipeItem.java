package com.example.cure.ui.home;

import com.example.cure.model.data.IRecipe;

public class DailyRecipeItem implements IRecipe {

    private final String id;
    private final String name;
    private final String image;
    private String type;
    private final int calories;
    private final int fat;
    private final int carbs;
    private int protein;
    private int yield;

    @Override
    public int getCalories() {
        return calories;
    }


    @Override
    public int getProtein() {
        return protein;
    }

    @Override
    public int getYield() {
        return yield;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public int getCarbs() {
        return carbs;
    }

    public DailyRecipeItem(String id, String name, String image,  String type, int calories, int protein, int fat, int carbs, int yield) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.yield = yield;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }


}
