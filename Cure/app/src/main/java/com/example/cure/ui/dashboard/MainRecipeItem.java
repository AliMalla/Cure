package com.example.cure.ui.dashboard;

public class MainRecipeItem {

    private String name;
    private String image;
    private String calories;
    private String fat;
    private String protein;
    private String carbs;
    private String time;

    public MainRecipeItem(String name, String image, String calories, String fat, String protein, String carbs, String time) {
        this.name = name;
        this.image = image;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCalories() {
        return calories;
    }

    public String getFat() {
        return fat;
    }

    public String getProtein() {
        return protein;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getTime() {
        return time;
    }
}
