package com.omnianobis.rickmorty.core.model.response.locations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationResponse {

    @SerializedName("info")
    @Expose
    private InfoLocation info;
    @SerializedName("results")
    @Expose
    private List<ResultLocation> results = null;

    public InfoLocation getInfo() {
        return info;
    }

    public void setInfo(InfoLocation info) {
        this.info = info;
    }

    public List<ResultLocation> getResults() {
        return results;
    }

    public void setResults(List<ResultLocation> results) {
        this.results = results;
    }
}