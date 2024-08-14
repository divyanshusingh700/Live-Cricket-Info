package com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models;

public class TeamsResult {
    private String date;
    private String matchStatus;
    private String team;
    private String url;
    private String venue;

    public TeamsResult() {
    }

    public TeamsResult(String str, String str2, String str3, String str4, String str5) {
        this.date = str;
        this.matchStatus = str2;
        this.venue = str3;
        this.team = str4;
        this.url = str5;
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