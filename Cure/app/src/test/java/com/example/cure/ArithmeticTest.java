package com.example.cure;

import static org.junit.Assert.assertEquals;

import com.example.cure.model.data.Nutrient;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.TotalNutrients;
import com.example.cure.model.other.Arithmetic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticTest {

    private Arithmetic arithmetic = new Arithmetic();

    @Test
    public void calculateTotalCaloriesTest(){
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("fish","image","uri",250,12,19,null,null,null,null,null));
        recipes.add(new Recipe("chicken","image","uri",300,30,15,null,null,null,null,null));
        recipes.add(new Recipe("ris","image","uri",350,50,35,null,null,null,null,null));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40,null,null,null,null,null));

        double totalCalories = arithmetic.calculateTotalCalories(recipes);

        assertEquals(167, totalCalories,0.000000001);

    }

    @Test
    public void calculateTotalCarbsTest(){

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("fish","image","uri",250,12,19, 0, null,null,null,null,null,null,null,null,totalNutrients));
        recipes.add(new Recipe("chicken","image","uri",300,30,15, 0, null,null,null,null,null, null,null,null,totalNutrients1));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 0, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 0, null,null,null,null,null,null,null,null,totalNutrients3));

        double totalCarbs = arithmetic.calculateTotalCarbs(recipes);

        assertEquals(280, totalCarbs,0.000000001);
    }

    @Test
    public void calculateTotalFatTest(){
        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("fish","image","uri",250,12,19, 0, null,null,null,null,null,null,null,null,totalNutrients));
        recipes.add(new Recipe("chicken","image","uri",300,30,15, 0, null,null,null,null,null, null,null,null,totalNutrients1));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 0, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 0, null,null,null,null,null,null,null,null,totalNutrients3));

        double totalFat = arithmetic.calculateTotalFat(recipes);

        assertEquals(245, totalFat,0.000000001);
    }

    @Test
    public void calculateTotalProteinTest(){
        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",30,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",40,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",50,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("fish","image","uri",250,12,19, 0, null,null,null,null,null,null,null,null,totalNutrients));
        recipes.add(new Recipe("chicken","image","uri",300,30,15, 0, null,null,null,null,null, null,null,null,totalNutrients1));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 0, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 0, null,null,null,null,null,null,null,null,totalNutrients3));

        double totalProtein = arithmetic.calculateTotalProtein(recipes);

        assertEquals(145, totalProtein,0.000000001);
    }

}
