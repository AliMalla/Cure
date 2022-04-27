package com.example.cure.model.server.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "cure.db";
    private final static String TABLE_PREVIOUS_RECIPES = "previousrecipes";



    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable android.database.sqlite.SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PREVIOUS_RECIPES + "(" +
        "_id TEXT, date TEXT, PRIMARY KEY(_id, date) );";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PREVIOUS_RECIPES);
    }


    public void addRecipe(String id, String date) {
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put("date", date);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PREVIOUS_RECIPES, null, values);

        db.close();
    }


    public void deleteRecipe(String id, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PREVIOUS_RECIPES, "_id=? AND date=?", new String[]{id,date});
        db.close();

    }


    public List<String> getRecipes(String date) {
        List<String> recipes = new ArrayList<>();

        final String query = "SELECT _id FROM " + TABLE_PREVIOUS_RECIPES + " WHERE date=" + date + ";";

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            int i = cursor.getColumnIndex("_id");
            String recipe = cursor.getString(i);
            recipes.add(recipe);

            cursor.moveToNext();
        }

        return recipes;
    }
}
