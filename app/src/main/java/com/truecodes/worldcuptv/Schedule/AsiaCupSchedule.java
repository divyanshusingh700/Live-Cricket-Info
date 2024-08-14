package com.truecodes.worldcuptv.Schedule;


public class AsiaCupSchedule {
    private String date;
    private String matchRunning;
    private String matchStatus;
    private String team;
    private String url;
    private String venue;

    public AsiaCupSchedule() {
    }

    public AsiaCupSchedule(String str, String str2, String str3, String str4, String str5, String str6) {
        this.date = str;
        this.matchStatus = str2;
        this.matchRunning = str3;
        this.venue = str4;
        this.team = str5;
        this.url = str6;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}