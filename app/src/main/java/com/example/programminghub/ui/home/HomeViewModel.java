package com.example.programminghub.ui.home;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.programminghub.DBhelper;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }


    public LiveData<String> getText() {
        return mText;
    }
}