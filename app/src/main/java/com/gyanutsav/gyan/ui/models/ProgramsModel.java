package com.gyanutsav.gyan.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramsModel implements Parcelable {

    @SerializedName("Programs_id")
    @Expose
    String Programs_id;

    @SerializedName("Programs_title")
    @Expose
    String Programs_title;

    @SerializedName("Theme")
    @Expose
    String Theme;

    @SerializedName("EntryFee")
    @Expose
    String EntryFee;

    @SerializedName("LastDate")
    @Expose
    String LastDate;

    @SerializedName("Programs_desc")
    @Expose
    String Programs_desc;

    @SerializedName("Programs_pic")
    @Expose
    String Programs_pic;

    @SerializedName("create_at")
    @Expose
    String create_at;

    @SerializedName("Applystatus")
    @Expose
    int Applystatus;

    @SerializedName("filetype")
    @Expose
    String filetype;

    @SerializedName("partitype")
    @Expose
    int partitype;


    protected ProgramsModel(Parcel in) {
        Programs_id = in.readString();
        Programs_title = in.readString();
        Theme = in.readString();
        EntryFee = in.readString();
        LastDate = in.readString();
        Programs_desc = in.readString();
        Programs_pic = in.readString();
        create_at = in.readString();
        Applystatus = in.readInt();
        filetype = in.readString();
        partitype = in.readInt();
    }

    public static final Creator<ProgramsModel> CREATOR = new Creator<ProgramsModel>() {
        @Override
        public ProgramsModel createFromParcel(Parcel in) {
            return new ProgramsModel(in);
        }

        @Override
        public ProgramsModel[] newArray(int size) {
            return new ProgramsModel[size];
        }
    };

    public String getPrograms_id() {
        return Programs_id;
    }

    public String getPrograms_title() {
        return Programs_title;
    }

    public String getTheme() {
        return Theme;
    }

    public String getEntryFee() {
        return EntryFee;
    }

    public String getLastDate() {
        return LastDate;
    }

    public String getPrograms_desc() {
        return Programs_desc;
    }

    public String getCreate_at() {
        return create_at;
    }

    public String getPrograms_pic() {
        return Programs_pic;
    }

    public String getFiletype() {
        return filetype;
    }

    public int getApplystatus() {
        return Applystatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Programs_id);
        parcel.writeString(Programs_title);
        parcel.writeString(Theme);
        parcel.writeString(EntryFee);
        parcel.writeString(LastDate);
        parcel.writeString(Programs_desc);
        parcel.writeString(Programs_pic);
        parcel.writeString(create_at);
        parcel.writeInt(Applystatus);
        parcel.writeString(filetype);
        parcel.writeInt(partitype);
    }

    public void setPrograms_id(String programs_id) {
        Programs_id = programs_id;
    }

    public void setPrograms_title(String programs_title) {
        Programs_title = programs_title;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public void setEntryFee(String entryFee) {
        EntryFee = entryFee;
    }

    public void setLastDate(String lastDate) {
        LastDate = lastDate;
    }

    public void setPrograms_desc(String programs_desc) {
        Programs_desc = programs_desc;
    }

    public void setPrograms_pic(String programs_pic) {
        Programs_pic = programs_pic;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public void setApplystatus(int applystatus) {
        Applystatus = applystatus;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }


    public int getPartitype() {
        return partitype;
    }
}
