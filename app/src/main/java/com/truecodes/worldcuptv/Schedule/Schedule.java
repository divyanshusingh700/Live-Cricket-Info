package com.truecodes.worldcuptv.Schedule;


public class Schedule {
    private String date;
    private String location;
    private String match;
    private String matchUrl;
    private String teamAImage;
    private String teamAName;
    private String teamAScore;
    private String teamBImage;
    private String teamBName;
    private String teamBScore;

    public Schedule() {
    }

    public Schedule(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.date = str;
        this.match = str2;
        this.teamAName = str3;
        this.teamBName = str4;
        this.teamAScore = str5;
        this.teamBScore = str6;
        this.teamAImage = str7;
        this.teamBImage = str8;
        this.matchUrl = str9;
        this.location = str10;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getMatch() {
        return this.match;
    }

    public void setMatch(String str) {
        this.match = str;
    }

    public String getTeamAName() {
        return this.teamAName;
    }

    public void setTeamAName(String str) {
        this.teamAName = str;
    }

    public String getTeamBName() {
        return this.teamBName;
    }

    public void setTeamBName(String str) {
        this.teamBName = str;
    }

    public String getTeamAScore() {
        return this.teamAScore;
    }

    public void setTeamAScore(String str) {
        this.teamAScore = str;
    }

    public String getTeamBScore() {
        return this.teamBScore;
    }

    public void setTeamBScore(String str) {
        this.teamBScore = str;
    }

    public String getTeamAImage() {
        return this.teamAImage;
    }

    public void setTeamAImage(String str) {
        this.teamAImage = str;
    }

    public String getTeamBImage() {
        return this.teamBImage;
    }

    public void setTeamBImage(String str) {
        this.teamBImage = str;
    }

    public String getMatchUrl() {
        return this.matchUrl;
    }

    public void setMatchUrl(String str) {
        this.matchUrl = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }
}