package com.omnianobis.rickmorty.net;

import com.omnianobis.rickmorty.core.model.response.allcharacters.AllCharactersResponse;
import com.omnianobis.rickmorty.core.model.response.singleperson.SinglePersonResponse;
import com.omnianobis.rickmorty.utils.ApiConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRickMorty {

    @GET(ApiConstants.RICK_MORTY_CHARACTERS)
    Call<AllCharactersResponse> getAllCharacters();

    @GET(ApiConstants.RICK_MORTY_SINGLE_PERSON)
    Call<SinglePersonResponse> getPerson(@Path("owner") String owner);
}
