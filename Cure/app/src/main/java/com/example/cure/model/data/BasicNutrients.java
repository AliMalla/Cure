package com.example.cure.model.data;

public class BasicNutrients {

    private final String energy;
    private final String water;
    private final String sugars;
    private final String protein;
    private final String iron;
    private final String carbohydrate;
    private final String zinc;

    public BasicNutrients(String energy, String water, String sugars, String protein, String iron, String carbohydrate, String zinc) {
        this.energy = energy;
        this.water = water;
        this.sugars = sugars;
        this.protein = protein;
        this.iron = iron;
        this.carbohydrate = carbohydrate;
        this.zinc = zinc;
    }

    public String getEnergy() {
        return energy;
    }

    public String getWater() {
        return water;
    }

    public String getSugars() {
        return sugars;
    }

    public String getProtein() {
        return protein;
    }

    public String getIron() {
        return iron;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public String getZinc() {
        return zinc;
    }
}