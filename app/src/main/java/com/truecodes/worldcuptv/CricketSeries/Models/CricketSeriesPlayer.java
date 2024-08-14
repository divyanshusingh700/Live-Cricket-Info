package com.truecodes.worldcuptv.CricketSeries.Models;




public class CricketSeriesPlayer {
    private String playerName;
    private String playerStyle;
    private String playerURL;

    public CricketSeriesPlayer() {
    }

    public CricketSeriesPlayer(String str, String str2, String str3) {
        this.playerName = str;
        this.playerStyle = str2;
        this.playerURL = str3;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerStyle() {
        return this.playerStyle;
    }

    public void setPlayerStyle(String str) {
        this.playerStyle = str;
    }

    public String getPlayerURL() {
        return this.playerURL;
    }

    public void setPlayerURL(String str) {
        this.playerURL = str;
    }
}