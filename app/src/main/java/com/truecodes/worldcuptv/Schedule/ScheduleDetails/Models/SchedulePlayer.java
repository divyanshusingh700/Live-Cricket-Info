package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models;


public class SchedulePlayer {
    private String playerName;
    private String playerURL;

    public SchedulePlayer() {
    }

    public SchedulePlayer(String str, String str2) {
        this.playerName = str;
        this.playerURL = str2;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerURL() {
        return this.playerURL;
    }

    public void setPlayerURL(String str) {
        this.playerURL = str;
    }
}