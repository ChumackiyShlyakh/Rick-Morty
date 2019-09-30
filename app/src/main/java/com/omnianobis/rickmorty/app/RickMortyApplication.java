package com.omnianobis.rickmorty.app;

import android.app.Application;

import com.omnianobis.rickmorty.net.RestClient;

public class RickMortyApplication extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
    }

    public static RestClient getRestClientInstance() {
        return restClient;
    }
}