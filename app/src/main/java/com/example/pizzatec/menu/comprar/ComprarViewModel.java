package com.example.pizzatec.menu.comprar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComprarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComprarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Comprar");
    }

    public LiveData<String> getText() {
        return mText;
    }
}