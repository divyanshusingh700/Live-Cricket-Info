package com.truecodes.worldcuptv.Schedule.Models;


public class Live {
    private String date;
    private String matchNo;
    private String matchRunning;
    private String matchStatus;
    private String scoreA;
    private String scoreB;
    private String team;
    private String url;
    private String venue;

    public Live() {
    }

    public Live(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.date = str;
        this.matchStatus = str2;
        this.matchRunning = str3;
        this.venue = str4;
        this.team = str5;
        this.matchNo = str6;
        this.url = str7;
        this.scoreA = str8;
        this.scoreB = str9;
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

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String str) {
        this.team = str;
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

    public void setUrl(String str) {
        this.url = str;
    }

    public String getScoreA() {
        return this.scoreA;
    }

    public void setScoreA(String str) {
        this.scoreA = str;
    }

    public String getScoreB() {
        return this.scoreB;
    }

    public void setScoreB(String str) {
        this.scoreB = str;
    }
}