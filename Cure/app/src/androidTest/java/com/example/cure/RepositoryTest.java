package com.example.cure;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cure.model.data.Recipe;
import com.example.cure.model.server.database.Repository;


import java.util.GregorianCalendar;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * A test class for Repository.java
 *
 * @author Ali Alkhaled
 * @author Ali Malla
 */
@RunWith(AndroidJUnit4.class)
public class RepositoryTest {


    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private Repository rep = Repository.getInstance(appContext);

    private Recipe testRecipe1;

    private Recipe testRecipe2;


    @Before
    public void destroyDataBase() {

        appContext.deleteDatabase("cure.db");
        setUp();
    }

    private void setUp(){
        testRecipe1 = getRecipe("test1", "test1","http://www.edamam.com/ontologies/edamam.owl#recipe_b66666d5c882ca199f43def8f1b8a03f");
        testRecipe2 = getRecipe("test2", "test2", "http://www.edamam.com/ontologies/edamam.owl#recipe_e7e22b5f9afdae010472f2084a76fd6c");
    }

    @Test
    public void useAppContext() {

        assertEquals("com.example.cure", appContext.getPackageName());
    }



    @Test
    public void addRecipeTest(){

        rep.addRecipe(testRecipe1, new GregorianCalendar(2021, 2, 8));

        List<String> recipes = rep.getRecipeIds("20210208");

        String res = recipes.get(0);

        assertEquals("b66666d5c882ca199f43def8f1b8a03f", res);

    }


    private Recipe getRecipe(String label, String image, String uri){

        String[]test = new String[1];
        Recipe recipe = new Recipe(label, image, uri, 0, 0, 0, test, test, test, test, test);

        return recipe;
    }



    @Test
    public void addRecipeMultipleAdditionsTest(){

        rep.addRecipe(testRecipe1, new GregorianCalendar(2022, 01, 03));
        rep.addRecipe(testRecipe2, new GregorianCalendar(2022, 01, 03));

        List<String> recipes = rep.getRecipeIds("20220103");

        assertEquals(2, recipes.size());
    }


    @Test
    public void addRecipeSameRecipesInDifferentDates(){

        rep.addRecipe(testRecipe1, new GregorianCalendar(2022, 01, 03));
        rep.addRecipe(testRecipe1, new GregorianCalendar(2022, 01, 07));
        rep.addRecipe(testRecipe1, new GregorianCalendar(2022, 05, 03));

        List<String> recipes1 = rep.getRecipeIds("20220103");
        List<String> recipes2 = rep.getRecipeIds("20220107");
        List<String> recipes3 = rep.getRecipeIds("20220503");

        int res = recipes1.size() + recipes2.size() + recipes3.size();

        assertEquals(3, res);
    }


    @Test
    public void deleteRecipeTest(){

        rep.addRecipe(testRecipe1, new GregorianCalendar(2022, 01, 03));

        rep.deleteRecipe(testRecipe1, new GregorianCalendar(2022, 01, 03));

        List<String> recipes = rep.getRecipeIds("20220103");

        assertEquals(0, recipes.size());
    }

    @Test
    public void deleteRecipeWithDifferentDateTest(){

        rep.addRecipe(testRecipe1, new GregorianCalendar(2022, 01, 04));

        rep.deleteRecipe(testRecipe1, new GregorianCalendar(2022, 01, 03));

        List<String> recipes = rep.getRecipeIds("20220104");

        assertEquals(1, recipes.size());
    }


}