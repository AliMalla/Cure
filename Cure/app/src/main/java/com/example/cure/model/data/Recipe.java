package com.example.cure.model.data;

import java.util.List;

public class Recipe {

    private final String label;
    private final String image;
    private final float totalWeight;
    private final float yield;
    private RecipeImages images;
    private RecipeSortingTypes sortingTypes;
    private TotalNutrients totalNutrients;
    private List<Ingredient> ingredients;
    private List<String> healthLabels;
    private List<Digest> digests;


    public Recipe(String label, String image, RecipeImages images, List<Ingredient> ingredients, float yield, float totalWeight,
                  RecipeSortingTypes sortingTypes, TotalNutrients totalNutrients, List<String> healthLabels, List<Digest> digests) {
        this.label = label;
        this.image = image;
        this.images = images;
        this.ingredients = ingredients;
        this.totalWeight = totalWeight;
        this.yield = yield;
        this.sortingTypes = sortingTypes;
        this.totalNutrients = totalNutrients;
        this.healthLabels = healthLabels;
        this.digests = digests;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public float getYield() {
        return yield;
    }

    public RecipeImages getImages() {
        return images;
    }

    public RecipeSortingTypes getSortingTypes() {
        return sortingTypes;
    }

    public TotalNutrients getTotalNutrients() {
        return totalNutrients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public List<Digest> getDigests() {
        return digests;
    }
}