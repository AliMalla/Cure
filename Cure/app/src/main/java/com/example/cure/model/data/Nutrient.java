package com.example.cure.model.data;

public class Nutrient {
    private final String label;
    private final float quantity;
    private final String unit;

    public Nutrient(String label, float quantity, String unit) {
        this.label = label;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getLabel() {
        return label;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue(){
        return (int)quantity + " " + unit;
    }
}