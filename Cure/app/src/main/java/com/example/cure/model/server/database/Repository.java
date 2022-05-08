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

    private DBHandler db;

    public Repository(Context context) {
        db = new DBHandler(context, null, null, 1);
    }


    private String getRecipeId(Recipe recipe) {
        final String temp = recipe.getUri();
        final String id = temp.substring(temp.length() - 32);

        return id;
    }


    /**
     * A method to add a new recipe into the database
     * @param recipe the recipe to be added
     * @param date the date when the recipe has been eaten/cooked of the user
     */
    public void addRecipe(Recipe recipe, Calendar date) {
        final String id = getRecipeId(recipe);
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
     * @return list of recipes
     */
    public List<Recipe> getRecipes(Calendar date) {

        db.addRecipe("b66666d5c882ca199f43def8f1b8a03f", "20220313");
        db.addRecipe("e7e22b5f9afdae010472f2084a76fd6c", "20220313");

        db.deleteRecipe("b66666d5c882ca199f43def8f1b8a03f", "20220313");
        db.deleteRecipe("e7e22b5f9afdae010472f2084a76fd6c", "20220313");
        return null;
    }

    /**
     * A method just for testing purpose
     * @param date date
     * @return list of ids
     */
    public List<String> getRecipeIds(String date) {
        return db.getRecipes(date);
    }




}
