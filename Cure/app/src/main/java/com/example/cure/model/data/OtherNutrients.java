package com.example.cure.model.data;

public class OtherNutrients {

    private final String calcium;
    private final String magnesium;
    private final String sodium;
    private final String potassium;
    private final String folate;
    private final String folicAcid;

    public OtherNutrients(String calcium, String magnesium, String sodium, String potassium, String folate, String folicAcid) {
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.sodium = sodium;
        this.potassium = potassium;
        this.folate = folate;
        this.folicAcid = folicAcid;
    }

    public String getCalcium() {
        return calcium;
    }

    public String getMagnesium() {
        return magnesium;
    }

    public String getSodium() {
        return sodium;
    }

    public String getPotassium() {
        return potassium;
    }

    public String getFolate() {
        return folate;
    }

    public String getFolicAcid() {
        return folicAcid;
    }
}
