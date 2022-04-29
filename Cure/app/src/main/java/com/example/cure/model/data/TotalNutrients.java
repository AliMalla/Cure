package com.example.cure.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalNutrients { /////CHANGE////////////

    @SerializedName("ENERC_KCAL")
    @Expose
    private Nutrient energy;

    @SerializedName("FAT")
    @Expose
    private Nutrient fat;

    @SerializedName("PROCNT")
    @Expose
    private Nutrient protein;

    @SerializedName("SUGAR")
    @Expose
    private Nutrient sugar;

    @SerializedName("CHOCDF")
    @Expose
    private Nutrient carbs;

    @SerializedName("WATER")
    @Expose
    private Nutrient water;

    @SerializedName("ZN")
    @Expose
    private Nutrient zinc;

    @SerializedName("CA")
    @Expose
    private Nutrient calcium;

    @SerializedName("FE")
    @Expose
    private Nutrient iron;

    @SerializedName("VITA_RAE")
    @Expose
    private Nutrient vitaminA;

    @SerializedName("VITB6A")
    @Expose
    private Nutrient vitaminB6;

    @SerializedName("VITC")
    @Expose
    private Nutrient vitaminC;

    @SerializedName("VITD")
    @Expose
    private Nutrient vitaminD;

    @SerializedName("TOCPHA")
    @Expose
    private Nutrient vitaminE;

    public TotalNutrients(Nutrient energy, Nutrient fat, Nutrient protein, Nutrient sugar, Nutrient carbs,
                          Nutrient water, Nutrient zinc, Nutrient calcium, Nutrient iron, Nutrient vitaminA,
                          Nutrient vitaminB6, Nutrient vitaminC, Nutrient vitaminD, Nutrient vitaminE) {
        this.energy = energy;
        this.fat = fat;
        this.protein = protein;
        this.sugar = sugar;
        this.carbs = carbs;
        this.water = water;
        this.zinc = zinc;
        this.calcium = calcium;
        this.iron = iron;
        this.vitaminA = vitaminA;
        this.vitaminB6 = vitaminB6;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
    }

    public Nutrient getEnergy() {
        return energy;
    }

    public Nutrient getFat() {
        return fat;
    }

    public Nutrient getProtein() {
        return protein;
    }

    public Nutrient getSugar() {
        return sugar;
    }

    public Nutrient getCarbs() {
        return carbs;
    }

    public Nutrient getWater() {
        return water;
    }

    public Nutrient getZinc() {
        return zinc;
    }

    public Nutrient getCalcium() {
        return calcium;
    }

    public Nutrient getIron() {
        return iron;
    }

    public Nutrient getVitaminA() {
        return vitaminA;
    }

    public Nutrient getVitaminB6() {
        return vitaminB6;
    }

    public Nutrient getVitaminC() {
        return vitaminC;
    }

    public Nutrient getVitaminD() {
        return vitaminD;
    }

    public Nutrient getVitaminE() {
        return vitaminE;
    }

}
