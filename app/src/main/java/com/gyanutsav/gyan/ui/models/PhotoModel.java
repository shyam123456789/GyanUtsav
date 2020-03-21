package com.gyanutsav.gyan.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoModel {

    @SerializedName("gallary_pic")
    @Expose
    String gallary_pic;


   public String getPicture(){
       return gallary_pic;
   }
}
