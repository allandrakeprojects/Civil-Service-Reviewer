package com.psulccomsci.civilservicereviewer.ui.Vis_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel_Vis extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel_Vis() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}