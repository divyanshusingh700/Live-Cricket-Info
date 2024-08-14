package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models;


public class ManOfTheMatch {
    private String playerName;
    private String playerUrl;
    private String title;

    public ManOfTheMatch() {
    }

    public ManOfTheMatch(String str, String str2, String str3) {
        this.title = str;
        this.playerName = str2;
        this.playerUrl = str3;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerUrl() {
        return this.playerUrl;
    }

    public void setPlayerUrl(String str) {
        this.playerUrl = str;
    }
}