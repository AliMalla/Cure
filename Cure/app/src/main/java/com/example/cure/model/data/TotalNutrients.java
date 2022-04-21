package com.example.cure.model.data;

public class TotalNutrients {

    private BasicNutrients basicNutrients;
    private VitaminNutrients vitaminNutrients;
    private OtherNutrients otherNutrients;

    public TotalNutrients(BasicNutrients basicNutrients, VitaminNutrients vitaminNutrients, OtherNutrients otherNutrients) {
        this.basicNutrients = basicNutrients;
        this.vitaminNutrients = vitaminNutrients;
        this.otherNutrients = otherNutrients;
    }

    public BasicNutrients getBasicNutrients() {
        return basicNutrients;
    }

    public VitaminNutrients getVitaminNutrients() {
        return vitaminNutrients;
    }

    public OtherNutrients getOtherNutrients() {
        return otherNutrients;
    }
}
