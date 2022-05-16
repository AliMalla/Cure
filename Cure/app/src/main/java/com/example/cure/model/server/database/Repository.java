package com.example.cure.model.server.database;

import android.content.Context;

import com.example.cure.model.data.Recipe;
import com.example.cure.model.other.DataConverter;

import java.util.Calendar;
import java.util.List;

/**
 * A class to communicate with and access the database
 * @author Ali Alkhaled
 */
public class Repository {

    private static Repository repository;
    private DBHandler db;

    /*
    public Repository(Context context) {
        db = new DBHandler(context, null, null, 1);
    }

     */
    private Repository(Context context) {
        db = new DBHandler(context, null, null, 1);
    }



    public static Repository getInstance(Context context) {
        if(repository == null)
            repository = new Repository(context);

        return repository;
    }


    private String getRecipeId(Recipe recipe) {
        final String temp = recipe.getUri();
        final String id = temp.substring(temp.length() - 32);

        return id;
    }


    /**
     * A method to insert a new recipe into the database
     * @param recipe the recipe to be added
     * @param date the date when the recipe has been eaten/cooked by the user
     */
    public void addRecipe(Recipe recipe, Calendar date) {
        final String id = getRecipeId(recipe);
        final String dateStr = DataConverter.dateToString(date);

        db.addRecipe(id, dateStr);

    }

    /**
     * A method to insert a new recipe into  the database by id
     * @param id the id of the recipe to be inserted
     * @param date the date when the recipe hes been eaten/cooked by the user
     */
    public void addRecipe(String id, Calendar date) {
        final String dateStr = DataConverter.dateToString(date);

        db.addRecipe(id, dateStr);

    }


    /**
     * A method to delete an existing recipe from the database
     * @param recipe the recipe to be deleted
     * @param date the date when the recipe was eaten (added to the database)
     */
    public void deleteRecipe(Recipe recipe, Calendar date) {
        final String id = getRecipeId(recipe);
        final String dateStr = DataConverter.dateToString(date);

        db.deleteRecipe(id, dateStr);
    }

    /**
     * A method to delete an existing recipe from the database by id
     * @param id the id of the recipe
     * @param date the date when the recipe was eaten (added to the database)
     */
    public void deleteRecipe(String id, Calendar date) {
        final String dateStr = DataConverter.dateToString(date);
        db.deleteRecipe(id, dateStr);
    }


    /**
     * A method to return all recipes in the database on a certain date
     * @param date the date where the recipes were eaten
     * @return list of recipe ids
     */
    public List<String> getRecipes(Calendar date) {

        String dateStr = DataConverter.dateToString(date);
        List<String> ids = db.getRecipes(dateStr);

        return ids;
    }


    /**
     * A method just for testing purpose
     * @param date date
     * @return list of ids
     */
    /*
    public List<String> getRecipeIds(String date) {
        db.addRecipe("b66666d5c882ca199f43def8f1b8a03f", "20220313");
        db.addRecipe("e7e22b5f9afdae010472f2084a76fd6c", "20220313");
        return db.getRecipes(date);
    }

     */




}
