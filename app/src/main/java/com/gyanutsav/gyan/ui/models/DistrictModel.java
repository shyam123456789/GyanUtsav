package com.gyanutsav.gyan.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictModel {

    /*
         "": "283",
                 "": "20",
                 "": "Agar"
    */
    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("stateID")
    @Expose
    String stateID;

    @SerializedName("name")
    @Expose
    String name;


    public String getId() {
        return id;
    }

    public String getStateID() {
        return stateID;
    }

    public String getName() {
        return name;
    }
}
