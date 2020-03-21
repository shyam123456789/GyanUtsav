package com.gyanutsav.gyan.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateModel {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("countryID")
    @Expose
    String countryID;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryID() {
        return countryID;
    }

    public StateModel() {
    }


    public StateModel(String id, String name, String countryID) {
        this.id = id;
        this.name = name;
        this.countryID = countryID;
    }
}
