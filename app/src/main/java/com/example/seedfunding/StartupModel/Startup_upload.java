package com.example.seedfunding.StartupModel;

public class Startup_upload {

    private String startupName,foundersName,teamMember1,teamMember2,teamMember3,teamMember4,teamMember5,StartupSummary,startupDomain;
    private String mImageUrl;
    private String startupId;

    public Startup_upload() {
    }

    public Startup_upload(String startupName, String foundersName, String teamMember1, String teamMember2, String teamMember3, String teamMember4, String teamMember5, String startupSummary,String startupDomain, String mImageUrl) {
        this.startupName = startupName;
        this.foundersName = foundersName;
        this.teamMember1 = teamMember1;
        this.teamMember2 = teamMember2;
        this.teamMember3 = teamMember3;
        this.teamMember4 = teamMember4;
        this.teamMember5 = teamMember5;
        //StartupSummary = startupSummary;
        this.StartupSummary=startupSummary;
        this.startupDomain=startupDomain;
        this.mImageUrl = mImageUrl;

    }

    public String getStartupId() {
        return startupId;
    }

    public void setStartupId(String startupId) {
        this.startupId = startupId;
    }

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public String getFoundersName() {
        return foundersName;
    }

    public void setFoundersName(String foundersName) {
        this.foundersName = foundersName;
    }

    public String getTeamMember1() {
        return teamMember1;
    }

    public void setTeamMember1(String teamMember1) {
        this.teamMember1 = teamMember1;
    }

    public String getTeamMember2() {
        return teamMember2;
    }

    public void setTeamMember2(String teamMember2) {
        this.teamMember2 = teamMember2;
    }

    public String getTeamMember3() {
        return teamMember3;
    }

    public void setTeamMember3(String teamMember3) {
        this.teamMember3 = teamMember3;
    }

    public String getTeamMember4() {
        return teamMember4;
    }

    public void setTeamMember4(String teamMember4) {
        this.teamMember4 = teamMember4;
    }

    public String getTeamMember5() {
        return teamMember5;
    }

    public void setTeamMember5(String teamMember5) {
        this.teamMember5 = teamMember5;
    }

    public String getStartupSummary() {
        return StartupSummary;
    }

    public void setStartupSummary(String startupSummary) {
        StartupSummary = startupSummary;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getstartupDomain() {
        return startupDomain;
    }

    public void setstartupDomain(String startupDomain) {
        this.startupDomain = startupDomain;
    }


}
