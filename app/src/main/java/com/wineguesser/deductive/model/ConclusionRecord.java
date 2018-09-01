package com.wineguesser.deductive.model;

import com.google.firebase.database.Exclude;

public class ConclusionRecord {

    @Exclude
    private String conclusionId;
    @Exclude
    private String userId;

    private String actualGrape;
    private String actualCountry;
    private String actualRegion;
    private String actualQuality;
    private int actualVintage;

    private String userConclusionGrape;
    private String userConclusionCountry;
    private String userConclusionRegion;
    private String userConclusionQuality;
    private String appConclusionGrape;

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

    public String getActualGrape() {
        return actualGrape;
    }

    public void setActualGrape(String actualGrape) {
        this.actualGrape = actualGrape;
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

    public int getActualVintage() {
        return actualVintage;
    }

    public void setActualVintage(int actualVintage) {
        this.actualVintage = actualVintage;
    }

    public String getUserConclusionGrape() {
        return userConclusionGrape;
    }

    public void setUserConclusionGrape(String userConclusionGrape) {
        this.userConclusionGrape = userConclusionGrape;
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

    public String getAppConclusionGrape() {
        return appConclusionGrape;
    }

    public void setAppConclusionGrape(String appConclusionGrape) {
        this.appConclusionGrape = appConclusionGrape;
    }
}
