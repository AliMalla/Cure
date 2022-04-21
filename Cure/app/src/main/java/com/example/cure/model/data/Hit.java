package com.example.cure.model.data;

public class Hit {

    private Recipe recipe;
    private Link self;
    private Link next;

    public Recipe getRecipe() {
        return recipe;
    }

    public Link getSelf() {
        return self;
    }

    public Link getNext() {
        return next;
    }

    public Hit(Recipe recipe, Link self, Link next) {
        this.recipe = recipe;
        this.self = self;
        this.next = next;
    }


}