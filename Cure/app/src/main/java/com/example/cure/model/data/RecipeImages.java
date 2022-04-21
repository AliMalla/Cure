package com.example.cure.model.data;

public class RecipeImages {

    private Image mainImage;
    private Image ingredientsPageImage;

    //..
    //.. Maybe more images if needed

    public RecipeImages(Image mainImage, Image ingredientsPageImage) {
        this.mainImage = mainImage;
        this.ingredientsPageImage = ingredientsPageImage;
    }

    public Image getMainImage() {
        return mainImage;
    }

    public Image getIngredientsPageImage() {
        return ingredientsPageImage;
    }
}
