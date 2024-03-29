package com.omnianobis.rickmorty.core.model.response.singleperson;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinglePersonResponse {

    @PrimaryKey
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("gender")
    @Expose
    private String gender;
//    @SerializedName("origin")
//    @Expose
//    private OriginPerson origin;
//    @SerializedName("location")
//    @Expose
//    private LocationPerson location;
    @SerializedName("image")
    @Expose
    private String image;
//    @SerializedName("episode")
//    @Expose
//    private List<String> episode = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created")
    @Expose
    private String created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public OriginPerson getOrigin() {
//        return origin;
//    }
//
//    public void setOrigin(OriginPerson origin) {
//        this.origin = origin;
//    }
//
//    public LocationPerson getLocation() {
//        return location;
//    }
//
//    public void setLocation(LocationPerson location) {
//        this.location = location;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public List<String> getEpisode() {
//        return episode;
//    }
//
//    public void setEpisode(List<String> episode) {
//        this.episode = episode;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
