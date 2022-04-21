package com.example.cure.model.data;

public class RecipeSortingTypes {

    private final String[] mealType;
    private final String[] dishType;
    private final String[] cuisineType;

    public RecipeSortingTypes(String[] mealType, String[] dishType, String[] cuisineType) {
        this.mealType = mealType;
        this.dishType = dishType;
        this.cuisineType = cuisineType;
    }

    public String[] getMealType() {
        return mealType;
    }

    public String[] getDishType() {
        return dishType;
    }

    public String[] getCuisineType() {
        return cuisineType;
    }
}