package com.gyanutsav.gyan.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainModel {

    @SerializedName("status")
    @Expose
    boolean status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("Type")
    @Expose
    int Type;

    @SerializedName("Userdetail5")
    @Expose
    ArrayList<ProgramsModel> programmodellist;

    @SerializedName("Userdetail9")
    @Expose
    ArrayList<NotificationModel> notificationModelslist;

    @SerializedName("Userdetail2")
    @Expose
    ArrayList<DistrictModel> districtModelslist;

    @SerializedName("Userdetail1")
    @Expose
    ArrayList<StateModel> stateModelslist;

    @SerializedName("Userdetail10")
    @Expose
    ArrayList<PhotoModel> photoModellist;

    @SerializedName("Userdetail12")
    @Expose
    ArrayList<UserProfile> userProfile;

    @SerializedName("Userdetail3")
    @Expose
    ArrayList<UserProfile> userProfile1;


    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ProgramsModel> getProgrammodellist() {
        return programmodellist;
    }

    public ArrayList<NotificationModel> getNotificationModelslist() {
        return notificationModelslist;
    }

    public ArrayList<DistrictModel> getDistrictModelslist() {
        return districtModelslist;
    }

    public ArrayList<StateModel> getStateModelslist() {
        return stateModelslist;
    }

    public ArrayList<PhotoModel> getPhotoModellist() {
        return photoModellist;
    }

    public int getType() {
        return Type;
    }

    public ArrayList<UserProfile> getUserProfile() {
        return userProfile;
    }

    public ArrayList<UserProfile> getUserProfile1() {
        return userProfile1;
    }


}
