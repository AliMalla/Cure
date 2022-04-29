package com.example.cure.model.data;

public class Hit {

    private Recipe recipe;
    private Links links;

    public Hit(Recipe recipe, Links links) {
        this.recipe = recipe;
        this.links = links;
    }


    public Recipe getRecipe() {
        return recipe;
    }

    public Links getLinks() {
        return links;
    }
}