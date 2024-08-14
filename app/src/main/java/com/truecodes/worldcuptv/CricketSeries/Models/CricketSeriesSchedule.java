package com.truecodes.worldcuptv.CricketSeries.Models;




public class CricketSeriesSchedule {
    private String aScore;
    private String bScore;
    private String date;
    private String matchNo;
    private String matchRunning;
    private String matchStatus;
    private String teams;
    private String url;
    private String venue;

    public CricketSeriesSchedule() {
    }

    public CricketSeriesSchedule(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.date = str;
        this.matchStatus = str2;
        this.matchRunning = str3;
        this.venue = str4;
        this.teams = str5;
        this.matchNo = str6;
        this.url = str7;
        this.aScore = str8;
        this.bScore = str9;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getMatchStatus() {
        return this.matchStatus;
    }

    public void setMatchStatus(String str) {
        this.matchStatus = str;
    }

    public String getMatchRunning() {
        return this.matchRunning;
    }

    public void setMatchRunning(String str) {
        this.matchRunning = str;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String str) {
        this.venue = str;
    }

    public String getTeams() {
        return this.teams;
    }

    public void setTeams(String str) {
        this.teams = str;
    }

    public String getMatchNo() {
        return this.matchNo;
    }

    public void setMatchNo(String str) {
        this.matchNo = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String link) {
        this.url = link;
    }

    public String getaScore() {
        return this.aScore;
    }

    public void setaScore(String str) {
        this.aScore = str;
    }

    public String getbScore() {
        return this.bScore;
    }

    public void setbScore(String str) {
        this.bScore = str;
    }
}