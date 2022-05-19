package com.example.cure.model.data;

/**
 *
 * @author Ali Alkhaled
 */
public class Recipe implements IRecipe{

    private final String label;
    private final String image;
    private final String uri;
    private final float totalWeight;
    private final float calories;
    private final float yield;
    private final double totalTime;
    private final String[] mealType;
    private final String[] dishType;
    private final String[] cuisineType;
    private final String[] dietLabels;
    private final String[] healthLabels;
    private Ingredient[] ingredients;
    private Digest[] digests;
    private RecipeImages images;
    private TotalNutrients totalNutrients;

    public Recipe(String label, String image, String uri, float totalWeight, float calories,
                  float yield, String[] mealType, String[] dishType, String[] cuisineType,
                  String[] dietLabels, String[] healthLabels) {
        this.label = label;
        this.image = image;
        this.uri = uri;
        this.totalWeight = totalWeight;
        this.calories = calories;
        this.yield = yield;
        this.mealType = mealType;
        this.dishType = dishType;
        this.cuisineType = cuisineType;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        totalTime = 0.0;
    }

    public Recipe(String label, String image, String uri, float totalWeight, float calories, float yield, double totalTime, String[] mealType,
                  String[] dishType, String[] cuisineType, String[] dietLabels, String[] healthLabels,
                  Digest[] digests, Ingredient[] ingredients, RecipeImages images,
                  TotalNutrients totalNutrients) {
        this.label = label;
        this.image = image;
        this.uri = uri;
        this.totalWeight = totalWeight;
        this.calories = calories;
        this.yield = yield;
        this.totalTime = totalTime;
        this.mealType = mealType;
        this.dishType = dishType;
        this.cuisineType = cuisineType;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.digests = digests;
        this.ingredients = ingredients;
        this.images = images;
        this.totalNutrients = totalNutrients;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public String getUri() {
        return uri;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public int getCalories() {
        return (int)calories;
    }

    @Override
    public int getFat() {
        return (int)getTotalNutrients().getFat().getQuantity();
    }

    @Override
    public int getProtein() {
        return (int)getTotalNutrients().getProtein().getQuantity();
    }

    @Override
    public int getCarbs() {
        return (int)getTotalNutrients().getCarbs().getQuantity();
    }

    public int getYield() {
        return (int)yield;
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

    public String[] getDietLabels() {
        return dietLabels;
    }

    public String[] getHealthLabels() {
        return healthLabels;
    }

    public Digest[] getDigests() {
        return digests;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public RecipeImages getImages() {
        return images;
    }

    public TotalNutrients getTotalNutrients() {
        return totalNutrients;
    }

    public double getTotalTime() {
        return totalTime;
    }
}