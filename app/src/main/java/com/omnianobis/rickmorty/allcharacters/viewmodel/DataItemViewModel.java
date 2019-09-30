package com.omnianobis.rickmorty.allcharacters.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.omnianobis.rickmorty.core.model.response.allcharacters.Result;

public class DataItemViewModel extends BaseObservable {

    private String imageString;
    public final ObservableField<String> nameRepo = new ObservableField<>();

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public DataItemViewModel(@NonNull Result dataModel) {
        nameRepo.set(dataModel.getName());
        setImageString(dataModel.getImage());
    }
}