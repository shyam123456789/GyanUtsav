package com.gyanutsav.gyan.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile implements Parcelable {


    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("mobile_veri")
    @Expose
    private String mobileVeri;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("fcm_tokenid")
    @Expose
    private String fcmTokenid;
    @SerializedName("user_state")
    @Expose
    private String userState;
    @SerializedName("user_distict")
    @Expose
    private String userDistict;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("user_pic")
    @Expose
    private String userPic;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;
    @SerializedName("profile_update")
    @Expose
    private String profileUpdate;

    @SerializedName("AadharNo")
    @Expose
    private String AadharNo;


    protected UserProfile(Parcel in) {
        userId = in.readString();
        userName = in.readString();
        userPhone = in.readString();
        password = in.readString();
        otp = in.readString();
        mobileVeri = in.readString();
        userEmail = in.readString();
        fcmTokenid = in.readString();
        userState = in.readString();
        userDistict = in.readString();
        fatherName = in.readString();
        dob = in.readString();
        education = in.readString();
        userPic = in.readString();
        userStatus = in.readString();
        createdDate = in.readString();
        updateDate = in.readString();
        profileUpdate = in.readString();
        AadharNo = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMobileVeri() {
        return mobileVeri;
    }

    public void setMobileVeri(String mobileVeri) {
        this.mobileVeri = mobileVeri;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFcmTokenid() {
        return fcmTokenid;
    }

    public void setFcmTokenid(String fcmTokenid) {
        this.fcmTokenid = fcmTokenid;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserDistict() {
        return userDistict;
    }

    public void setUserDistict(String userDistict) {
        this.userDistict = userDistict;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getProfileUpdate() {
        return profileUpdate;
    }

    public void setProfileUpdate(String profileUpdate) {
        this.profileUpdate = profileUpdate;
    }


    public UserProfile(String userID,String userName, String userPhone, String password, String userEmail, String userState, String userDistict, String fatherName, String dob, String education, String userPic,String aadharNo) {
        this.userId = userID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.password = password;
        this.userEmail = userEmail;
        this.userState = userState;
        this.userDistict = userDistict;
        this.fatherName = fatherName;
        this.dob = dob;
        this.education = education;
        this.userPic = userPic;
        this.AadharNo = aadharNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(userName);
        parcel.writeString(userPhone);
        parcel.writeString(password);
        parcel.writeString(otp);
        parcel.writeString(mobileVeri);
        parcel.writeString(userEmail);
        parcel.writeString(fcmTokenid);
        parcel.writeString(userState);
        parcel.writeString(userDistict);
        parcel.writeString(fatherName);
        parcel.writeString(dob);
        parcel.writeString(education);
        parcel.writeString(userPic);
        parcel.writeString(userStatus);
        parcel.writeString(createdDate);
        parcel.writeString(updateDate);
        parcel.writeString(profileUpdate);
        parcel.writeString(AadharNo);
    }

    public String getAadharNo() {
        return AadharNo;
    }
}
