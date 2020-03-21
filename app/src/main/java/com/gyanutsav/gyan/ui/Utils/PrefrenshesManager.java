package com.gyanutsav.gyan.ui.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gyanutsav.gyan.ui.ApplicationClass;
import com.gyanutsav.gyan.ui.models.UserProfile;
import com.google.gson.Gson;

public class PrefrenshesManager {

    private static SharedPreferences getPreference() {
        return ApplicationClass.getAppContext().getSharedPreferences("GYANUTSAV", Context.MODE_PRIVATE);
    }


    public static void saveUserProfile(UserProfile userPofile) {
        getPreference().edit().putString("mProfile",new Gson().toJson(userPofile)).apply();
    }

    public static UserProfile getUserProfile() {
       String data =  getPreference().getString("mProfile",null);
       if (data!=null){
           return  new Gson().fromJson(data,UserProfile.class);
       }else {
           return null;
       }
    }


}
