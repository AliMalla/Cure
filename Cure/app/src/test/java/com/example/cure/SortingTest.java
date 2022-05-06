package com.example.cure;

import static org.junit.Assert.assertEquals;

import com.example.cure.model.data.Nutrient;
import com.example.cure.model.data.Recipe;
import com.example.cure.model.data.TotalNutrients;
import com.example.cure.model.other.Sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SortingTest {

    private Sorting sorting = new Sorting();

    @Test
    public void sortRecipesByTimeAscendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,null));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,null));

        sorting.sortRecipesByTimeAscendingOrder(recipes);

        assertEquals("fish", recipes.get(0).getLabel());
    }


    @Test
    public void sortRecipesByTimeDescendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,null));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,null));

        sorting.sortRecipesByTimeDescendingOrder(recipes);

        assertEquals("fish & ris", recipes.get(0).getLabel());
    }


    @Test
    public void sortRecipesByCaloriesAscendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,null));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,null));

        sorting.sortRecipesByCaloriesAscendingOrder(recipes);

        assertEquals("fish", recipes.get(0).getLabel());
    }


    @Test
    public void sortRecipesByCaloriesDescendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,null));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,null));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,null));

        sorting.sortRecipesByCaloriesDescendingOrder(recipes);

        assertEquals("fish & ris", recipes.get(0).getLabel());
    }



    @Test
    public void sortRecipesByFatAscendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,totalNutrients));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,totalNutrients1));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,totalNutrients3));

        sorting.sortRecipesByFatAscendingOrder(recipes);

        assertEquals("chicken", recipes.get(0).getLabel());
    }


    @Test
    public void sortRecipesByFatDescendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,totalNutrients));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,totalNutrients1));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,totalNutrients3));

        sorting.sortRecipesByFatDescendingOrder(recipes);

        assertEquals("ris", recipes.get(0).getLabel());
    }

    @Test
    public void sortRecipesByCarbsAscendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,totalNutrients));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,totalNutrients1));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,totalNutrients3));

        sorting.sortRecipesByCarbsAscendingOrder(recipes);

        assertEquals("chicken", recipes.get(0).getLabel());
    }


    @Test
    public void sortRecipesByCarbsDescendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,totalNutrients));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,totalNutrients1));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,totalNutrients3));

        sorting.sortRecipesByCarbsDescendingOrder(recipes);

        assertEquals("ris", recipes.get(0).getLabel());
    }

    @Test
    public void sortRecipesByProteinAscendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",26,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",75,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",65,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,totalNutrients));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,totalNutrients1));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,totalNutrients3));

        sorting.sortRecipesByProteinAscendingOrder(recipes);

        assertEquals("fish & ris", recipes.get(0).getLabel());
    }


    @Test
    public void sortRecipesByProteinDescendingOrderTest(){
        List <Recipe> recipes = new ArrayList<>();

        TotalNutrients totalNutrients = new TotalNutrients(null,new Nutrient("fat",50,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",40,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients1 = new TotalNutrients(null,new Nutrient("fat",60,"g"),new Nutrient("protein",50,"g"),
                null,new Nutrient("carb",70,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients2 = new TotalNutrients(null,new Nutrient("fat",55,"g"),new Nutrient("protein",25,"g"),
                null,new Nutrient("carb",80,"g"),null,null,null,null,null,null,null,null,null);
        TotalNutrients totalNutrients3 = new TotalNutrients(null,new Nutrient("fat",80,"g"),new Nutrient("protein",65,"g"),
                null,new Nutrient("carb",90,"g"),null,null,null,null,null,null,null,null,null);


        recipes.add(new Recipe("chicken","image","uri",300,30,15, 30, null,null,null,null,null, null,null,null,totalNutrients));
        recipes.add(new Recipe("fish","image","uri",250,12,19, 20, null,null,null,null,null,null,null,null,totalNutrients1));
        recipes.add(new Recipe("fish & ris","image","uri",500,75,40, 40, null,null,null,null,null,null,null,null,totalNutrients2));
        recipes.add(new Recipe("ris","image","uri",350,50,35, 25, null,null,null,null,null,null,null,null,totalNutrients3));

        sorting.sortRecipesByFatDescendingOrder(recipes);

        assertEquals("ris", recipes.get(0).getLabel());
    }




}
