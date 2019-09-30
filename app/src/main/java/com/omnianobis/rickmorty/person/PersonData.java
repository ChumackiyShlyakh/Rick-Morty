package com.omnianobis.rickmorty.person;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.omnianobis.rickmorty.core.AppExecutors;
import com.omnianobis.rickmorty.core.model.response.singleperson.SinglePersonResponse;
import com.omnianobis.rickmorty.net.ApiResponse;
import com.omnianobis.rickmorty.net.NetworkBoundResource;
import com.omnianobis.rickmorty.net.Resource;

import java.util.List;

public class PersonData {

    private AppExecutors appExecutors;

    public PersonData(Application application) {
        this.appExecutors = new AppExecutors();
    }

    public LiveData<Resource<List<SinglePersonResponse>>> loadPerson(String personNumber) {

        return new NetworkBoundResource<List<SinglePersonResponse>, List<SinglePersonResponse>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<SinglePersonResponse> item) {
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SinglePersonResponse> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<SinglePersonResponse>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<SinglePersonResponse>>> createCall() {
                return null;
            }
        }.asLiveData();
    }
}