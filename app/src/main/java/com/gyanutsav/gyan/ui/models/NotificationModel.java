package com.gyanutsav.gyan.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("notification_id")
    @Expose
    String notification_id;

    @SerializedName("notification_title")
    @Expose
    String notification_title;

    @SerializedName("notification_desc")
    @Expose
    String notification_desc;

    @SerializedName("noti_date")
    @Expose
    String noti_date;


    public String getNotification_id() {
        return notification_id;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public String getNotification_desc() {
        return notification_desc;
    }

    public String getNoti_date() {
        return noti_date;
    }
}
