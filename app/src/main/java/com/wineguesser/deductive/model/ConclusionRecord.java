package com.wineguesser.deductive.model;

public class ConclusionRecord {

    private String actualGrape;
    private String userConclusionGrape;
    private String appConclusionGrape;

    public ConclusionRecord() {

    }

    public String getActualGrape() {
        return actualGrape;
    }

    public void setActualGrape(String actualGrape) {
        this.actualGrape = actualGrape;
    }

    public String getUserConclusionGrape() {
        return userConclusionGrape;
    }

    public void setUserConclusionGrape(String userConclusionGrape) {
        this.userConclusionGrape = userConclusionGrape;
    }

    public String getAppConclusionGrape() {
        return appConclusionGrape;
    }

    public void setAppConclusionGrape(String appConclusionGrape) {
        this.appConclusionGrape = appConclusionGrape;
    }
}
