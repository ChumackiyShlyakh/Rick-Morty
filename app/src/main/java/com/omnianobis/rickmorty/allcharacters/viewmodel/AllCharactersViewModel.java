package com.omnianobis.rickmorty.allcharacters.viewmodel;

import android.app.Application;
import android.databinding.Bindable;

import com.omnianobis.rickmorty.BR;
import com.omnianobis.rickmorty.allcharacters.adapter.AllCharactersAdapter;
import com.omnianobis.rickmorty.core.arch.BaseAndroidViewModel;
import com.omnianobis.rickmorty.core.model.response.allcharacters.Result;

import java.util.List;

public class AllCharactersViewModel extends BaseAndroidViewModel {

    private AllCharactersAdapter adapter;

    public AllCharactersViewModel(Application application) {
        super(application);
        adapter = new AllCharactersAdapter();
    }

    @Bindable
    public AllCharactersAdapter getAdapter() {
        return adapter;
    }

    public void setUpFollowing(List<Result> data) {
        adapter.setFollowingResponse(data, getApplication());
        notifyPropertyChanged(BR.adapter);
    }
}