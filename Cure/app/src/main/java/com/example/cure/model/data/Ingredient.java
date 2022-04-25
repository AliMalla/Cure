package com.example.cure.model.data;

public class Ingredient {

    private final String text;
    private final String measure;
    private final String food;
    private final String foodId;
    private final float quantity;
    private final float weight;

    public Ingredient(String text, String measure, String food, String foodId, float quantity, float weight) {
        this.text = text;
        this.measure = measure;
        this.food = food;
        this.foodId = foodId;
        this.quantity = quantity;
        this.weight = weight;
    }

    public String getText() {
        return text;
    }

    public String getMeasure() {
        return measure;
    }

    public String getFood() {
        return food;
    }

    public String getFoodId() {
        return foodId;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getWeight() {
        return weight;
    }
}

