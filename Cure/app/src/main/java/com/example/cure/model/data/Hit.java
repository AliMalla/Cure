package com.example.cure.model.data;

public class Hit {

    private Recipe recipe;
    private Links self;
    private Links next;

    public Recipe getRecipe() {
        return recipe;
    }

    public Links getSelf() {
        return self;
    }

    public Links getNext() {
        return next;
    }

    public Hit(Recipe recipe, Links self, Links next) {
        this.recipe = recipe;
        this.self = self;
        this.next = next;
    }


}