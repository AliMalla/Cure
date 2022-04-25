package com.example.cure.model.data;

public class Image {

    private final String url;
    private final double width;
    private final double height;

    public Image(String url, double width, double height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}