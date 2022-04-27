package com.example.cure;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cure.model.server.database.DBHandler;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private DBHandler db = new DBHandler(appContext, null, null, 1);


    @Before
    public void destroyDataBase() {
        appContext.deleteDatabase("cure.db");
    }



    @Test
    public void useAppContext() {

        assertEquals("com.example.cure", appContext.getPackageName());
    }


    @Test
    public void addRecipeTest(){

        db.addRecipe("a1b2c3d4e5", "20220103");

        List<String> recipes = db.getRecipes("20220103");

        String res = recipes.get(0);

        assertEquals(res, "a1b2c3d4e5");
    }


    @Test
    public void addRecipeMultipleAdditionsTest(){

        db.addRecipe("a1b2c3d4e5", "20220103");
        db.addRecipe("a1b2c3d4e5", "20220103");
        db.addRecipe("a1b2c3d4e5", "20220103");
        db.addRecipe("a1b2c3d4e5", "20220103");

        List<String> recipes = db.getRecipes("20220103");


        assertEquals(1, recipes.size());
    }

    @Test
    public void addRecipeSameRecipesInDifferentDates(){

        db.addRecipe("a1b2c3d4e5", "20220103");
        db.addRecipe("a1b2c3d4e5", "20220107");
        db.addRecipe("a1b2c3d4e5", "20220503");

        List<String> recipes1 = db.getRecipes("20220103");
        List<String> recipes2 = db.getRecipes("20220107");
        List<String> recipes3 = db.getRecipes("20220503");

        int res = recipes1.size() + recipes2.size() + recipes3.size();

        assertEquals(3, res);
    }


    @Test
    public void deleteRecipeTest(){

        db.addRecipe("aaa", "20220103");

        db.deleteRecipe("aaa", "20220103");

        List<String> recipes = db.getRecipes("20220103");

        assertEquals(0, recipes.size());
    }

    @Test
    public void deleteRecipeWithDifferentDateTest(){

        db.addRecipe("aaa", "20220104");

        db.deleteRecipe("aaa", "20220103");

        List<String> recipes = db.getRecipes("20220104");

        assertEquals(1, recipes.size());
    }

}