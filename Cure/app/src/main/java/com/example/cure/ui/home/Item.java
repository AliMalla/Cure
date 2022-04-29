package com.example.cure.ui.home;

public class Item {

    private final String id;
    private final String name;
    private final String image;
    private final int calories;
    private Type type;

    public Item(String id, String name, String image, int calories, Type type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.calories = calories;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    enum Type {
        BREAKFAST, LUNCH, DINNER, OTHER
    }
}
