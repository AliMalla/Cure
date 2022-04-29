package com.example.cure.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cure.model.data.Recipe;
import com.example.cure.model.server.database.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Repository repository;

    public HomeViewModel(Context context) {
        mText = new MutableLiveData<>();
        mText.setValue("This is home page");
        repository = new Repository(context);
    }

    public LiveData<String> getText() {
        return mText;
    }


    public void deleteRecipe(Recipe recipe, Calendar calendar){

        repository.deleteRecipe(recipe,calendar);
    }
}