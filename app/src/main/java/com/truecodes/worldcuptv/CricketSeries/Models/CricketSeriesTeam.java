package com.truecodes.worldcuptv.CricketSeries.Models;



public class CricketSeriesTeam {
    private String teamName;
    private String teamURL;

    public CricketSeriesTeam() {
    }

    public CricketSeriesTeam(String str, String str2) {
        this.teamName = str;
        this.teamURL = str2;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String str) {
        this.teamName = str;
    }

    public String getTeamURL() {
        return this.teamURL;
    }

    public void setTeamURL(String str) {
        this.teamURL = str;
    }
}