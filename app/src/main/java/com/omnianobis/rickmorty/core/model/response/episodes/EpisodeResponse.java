package com.omnianobis.rickmorty.core.model.response.episodes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EpisodeResponse {

    @SerializedName("info")
    @Expose
    private InfoEpisode info;
    @SerializedName("results")
    @Expose
    private List<ResultEpisode> results = null;

    public InfoEpisode getInfo() {
        return info;
    }

    public void setInfo(InfoEpisode info) {
        this.info = info;
    }

    public List<ResultEpisode> getResults() {
        return results;
    }

    public void setResults(List<ResultEpisode> results) {
        this.results = results;
    }

}