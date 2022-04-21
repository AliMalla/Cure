package com.example.cure.model.data;

public class Digest {

    private final String label;
    private final String tag;
    private final String schemaOrgTag;
    private final String unit;
    private final float total;
    private final float daily;
    private final boolean hasRDI;


    public Digest(String label, String tag, String schemaOrgTag, String unit, float total, float daily, boolean hasRDI) {
        this.label = label;
        this.tag = tag;
        this.schemaOrgTag = schemaOrgTag;
        this.unit = unit;
        this.total = total;
        this.daily = daily;
        this.hasRDI = hasRDI;
    }

    public String getLabel() {
        return label;
    }

    public String getTag() {
        return tag;
    }

    public String getSchemaOrgTag() {
        return schemaOrgTag;
    }

    public String getUnit() {
        return unit;
    }

    public float getTotal() {
        return total;
    }

    public float getDaily() {
        return daily;
    }

    public boolean isHasRDI() {
        return hasRDI;
    }
}
