package com.example.cure.ui.home;

public class DailyRecipeItem {

    private final String id;
    private final String name;
    private final String image;
    private final int calories;
    private final int carbs;
    private final int fat;
    private final int protein;
    private String type;

    public DailyRecipeItem(String id, String name, String image, int calories, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.calories = calories;
        this.type = type;
        this.carbs = 0;
        this.fat = 0;
        this.protein = 0;
    }

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
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.type = type;
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

    public int getCarbs() {
        return carbs;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }
}
