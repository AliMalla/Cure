package com.example.cure.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home page");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public void deleteRecipe(String recipeId){
        List<String> recipes = new ArrayList<>();
        for (String rec: recipes){
            if(rec.equals(recipeId)){
                // delete recipe
            }
        }
    }
}