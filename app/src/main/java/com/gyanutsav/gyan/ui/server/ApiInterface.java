package com.gyanutsav.gyan.ui.server;

import com.gyanutsav.gyan.ui.models.MainModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("UserRegistration")
    Call<MainModel> userregistration(@Field("user_phone") String user_phone);

    @FormUrlEncoded
    @POST("user_otp")
    Call<MainModel> user_otp(@Field("user_phone") String user_phone
            , @Field("otp") String otp);

    @FormUrlEncoded
    @POST("district")
    Call<MainModel> getDistrict(@Field("id") String Stateid);

    //@FormUrlEncoded
    @POST("State")
    Call<MainModel> getState();

    @FormUrlEncoded
    @POST("Profilefetch")
    Call<MainModel> getProfile(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Userfcm_id")
    Call<MainModel> upadatefcm(@Field("user_id") String user_id, @Field("fcm_id") String fcm_id);

    @FormUrlEncoded
    @POST("U_sign")
    Call<MainModel> User_sign(@Field("user_phone") String user_phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("Forgotpassword")
    Call<MainModel> Forgotpassword(@Field("user_phone") String user_phone);


    @FormUrlEncoded
    @POST("Resendotp")
    Call<MainModel> Resendotp(@Field("user_phone") String user_phone);


    @FormUrlEncoded
    @POST("UpdatePassword")
    Call<MainModel> UpdatePassword(@Field("user_phone") String user_phone, @Field("password") String password);


     @FormUrlEncoded
    @POST("Programs")
    Call<MainModel> Programs(@Field("user_id") String user_id);


     @FormUrlEncoded
    @POST("Apply")
    Call<MainModel> Apply(@Field("user_id") String user_id,  @Field("tnc") String tnc,@Field("Programs_id") String Programs_id);

    @GET("notification")
    Call<MainModel> notification();

    @Multipart
    @POST("UpateProfile")
    Call<MainModel> UpateProfile(@Part("user_phone") RequestBody user_phone,
                                 @Part("user_name") RequestBody user_name,
                                 @Part("father_name") RequestBody father_name,
                                 @Part("dob") RequestBody dob,
                                 @Part("user_distict") RequestBody user_distict,
                                 @Part("user_state") RequestBody user_state,
                                 @Part("password") RequestBody password,
                                 @Part("education") RequestBody education,
                                 @Part("user_email") RequestBody user_email,
                                 @Part("AadharNo") RequestBody AadharNo,
                                 @Part MultipartBody.Part file
    );

    @Multipart
    @POST("submitprogram")
    Call<MainModel> submitprogram(@Part("user_id") RequestBody user_phone,
                                 @Part("Programs_id") RequestBody education,
                                 @Part MultipartBody.Part file
    );



    @GET("gallary")
    Call<MainModel> getPhotos();
}
