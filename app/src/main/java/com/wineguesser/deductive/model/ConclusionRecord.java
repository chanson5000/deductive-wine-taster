package com.wineguesser.deductive.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class ConclusionRecord implements Parcelable {

    @Exclude
    private String conclusionId;
    @Exclude
    private String userId;

    private String actualLabel;
    private String actualVariety;
    private String actualCountry;
    private String actualRegion;
    private String actualQuality;
    private Integer actualVintage;

    private String userConclusionVariety;
    private String userConclusionCountry;
    private String userConclusionRegion;
    private String userConclusionQuality;
    private Integer userConclusionVintage;

    private String appConclusionVariety;

    public ConclusionRecord() {

    }

    public String getConclusionId() {
        return conclusionId;
    }

    public void setConclusionId(String conclusionId) {
        this.conclusionId = conclusionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActualLabel() {
        return actualLabel;
    }

    public void setActualLabel(String actualLabel) {
        this.actualLabel = actualLabel;
    }

    public String getActualVariety() {
        return actualVariety;
    }

    public void setActualVariety(String actualVariety) {
        this.actualVariety = actualVariety;
    }

    public String getActualCountry() {
        return actualCountry;
    }

    public void setActualCountry(String actualCountry) {
        this.actualCountry = actualCountry;
    }

    public String getActualRegion() {
        return actualRegion;
    }

    public void setActualRegion(String actualRegion) {
        this.actualRegion = actualRegion;
    }

    public String getActualQuality() {
        return actualQuality;
    }

    public void setActualQuality(String actualQuality) {
        this.actualQuality = actualQuality;
    }

    public Integer getActualVintage() {
        return actualVintage;
    }

    public void setActualVintage(Integer actualVintage) {
        this.actualVintage = actualVintage;
    }

    public String getUserConclusionVariety() {
        return userConclusionVariety;
    }

    public void setUserConclusionVariety(String userConclusionGrape) {
        this.userConclusionVariety = userConclusionGrape;
    }

    public String getUserConclusionCountry() {
        return userConclusionCountry;
    }

    public void setUserConclusionCountry(String userConclusionCountry) {
        this.userConclusionCountry = userConclusionCountry;
    }

    public String getUserConclusionRegion() {
        return userConclusionRegion;
    }

    public void setUserConclusionRegion(String userConclusionRegion) {
        this.userConclusionRegion = userConclusionRegion;
    }

    public String getUserConclusionQuality() {
        return userConclusionQuality;
    }

    public void setUserConclusionQuality(String userConclusionQuality) {
        this.userConclusionQuality = userConclusionQuality;
    }

    public Integer getUserConclusionVintage() {
        return userConclusionVintage;
    }

    public void setUserConclusionVintage(Integer userConclusionVintage) {
        this.userConclusionVintage = userConclusionVintage;
    }

    public String getAppConclusionVariety() {
        return appConclusionVariety;
    }

    public void setAppConclusionVariety(String appConclusionGrape) {
        this.appConclusionVariety = appConclusionGrape;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(conclusionId);
        dest.writeString(userId);
        dest.writeString(actualLabel);
        dest.writeString(actualVariety);
        dest.writeString(actualCountry);
        dest.writeString(actualRegion);
        dest.writeString(actualQuality);
        dest.writeInt(actualVintage);
        dest.writeString(userConclusionVariety);
        dest.writeString(userConclusionCountry);
        dest.writeString(userConclusionRegion);
        dest.writeString(userConclusionQuality);
        dest.writeInt(userConclusionVintage);
        dest.writeString(appConclusionVariety);
    }

    public static final Creator<ConclusionRecord> CREATOR = new Creator<ConclusionRecord>() {
        @Override
        public ConclusionRecord createFromParcel(Parcel in) {
            return new ConclusionRecord(in);
        }

        @Override
        public ConclusionRecord[] newArray(int size) {
            return new ConclusionRecord[size];
        }
    };

    public ConclusionRecord(Parcel in) {
        this.conclusionId = in.readString();
        this.userId = in.readString();
        this.actualLabel = in.readString();
        this.actualVariety = in.readString();
        this.actualCountry = in.readString();
        this.actualRegion = in.readString();
        this.actualQuality = in.readString();
        this.actualVintage = in.readInt();
        this.userConclusionVariety = in.readString();
        this.userConclusionCountry = in.readString();
        this.userConclusionRegion = in.readString();
        this.userConclusionQuality = in.readString();
        this.userConclusionVintage = in.readInt();
        this.appConclusionVariety = in.readString();
    }

}
