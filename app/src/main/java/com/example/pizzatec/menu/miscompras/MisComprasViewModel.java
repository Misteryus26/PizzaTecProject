package com.example.pizzatec.menu.miscompras;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisComprasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MisComprasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mis Compras");
    }

    public LiveData<String> getText() {
        return mText;
    }
}