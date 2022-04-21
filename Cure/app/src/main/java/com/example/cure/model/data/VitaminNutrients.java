package com.example.cure.model.data;

public class VitaminNutrients {

    private final String vitaminA;
    private final String vitaminB;
    private final String vitaminB6;
    private final String vitaminB12;
    private final String vitaminC;
    private final String vitaminD;
    private final String vitaminE;
    private final String vitaminK;

    public VitaminNutrients(String vitaminA, String vitaminB, String vitaminB6, String vitaminB12,
                            String vitaminC, String vitaminD, String vitaminE, String vitaminK) {
        this.vitaminA = vitaminA;
        this.vitaminB = vitaminB;
        this.vitaminB6 = vitaminB6;
        this.vitaminB12 = vitaminB12;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.vitaminK = vitaminK;
    }

    public String getVitaminA() {
        return vitaminA;
    }

    public String getVitaminB() {
        return vitaminB;
    }

    public String getVitaminB6() {
        return vitaminB6;
    }

    public String getVitaminB12() {
        return vitaminB12;
    }

    public String getVitaminC() {
        return vitaminC;
    }

    public String getVitaminD() {
        return vitaminD;
    }

    public String getVitaminE() {
        return vitaminE;
    }

    public String getVitaminK() {
        return vitaminK;
    }
}