package com.example.cure.ui.home;

public class DailyRecipeItem {

    private final String id;
    private final String name;
    private final String image;
    private final int calories;
    private String type;
    private final int fat;
    private final int carbs;
    private final int protein;

    public DailyRecipeItem(String id,
                           String name,
                           String image,
                           int calories,
                           int carbs,
                           int fat,
                           int protein,
                           String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.calories = calories;
        this.type = type;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
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

    public int getCalories() {
        return calories;
    }

    public String getType() {
        return type;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getProtein() {
        return protein;
    }
}
