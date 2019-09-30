package com.omnianobis.rickmorty.allcharacters.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omnianobis.rickmorty.R;
import com.omnianobis.rickmorty.allcharacters.viewmodel.AllCharactersViewModel;
import com.omnianobis.rickmorty.app.RickMortyApplication;
import com.omnianobis.rickmorty.core.model.response.allcharacters.AllCharactersResponse;
import com.omnianobis.rickmorty.databinding.FragmentAllcharactersBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCharactersFragment extends Fragment implements Callback<AllCharactersResponse> {

    private Callback<AllCharactersResponse> userCallback;

    AllCharactersViewModel allCharactersViewModel;
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentAllcharactersBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_allcharacters,
                container, false);

        allCharactersViewModel = ViewModelProviders.of(this, viewModelFactory).get(AllCharactersViewModel.class);

        userCallback = this;

        fragmentBinding.setViewmodel(allCharactersViewModel);
        fragmentBinding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
        RickMortyApplication.getRestClientInstance().getApiRickMorty().getAllCharacters().enqueue(userCallback);

        return fragmentBinding.getRoot();
    }

    @Override
    public void onResponse(Call<AllCharactersResponse> call, Response<AllCharactersResponse> response) {
        AllCharactersResponse user = response.body();
        if (user != null) {
            allCharactersViewModel.setUpFollowing(response.body().getResults());
        } else {
            Toast.makeText(getContext(), "Hello", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<AllCharactersResponse> call, Throwable t) {
        Toast.makeText(getActivity(), R.string.something_is_wrong, Toast.LENGTH_LONG).show();
    }
}