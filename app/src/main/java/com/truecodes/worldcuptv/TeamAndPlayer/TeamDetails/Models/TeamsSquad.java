package com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models;

public class TeamsSquad {
    private String playerImage;
    private String playerName;
    private String playerUrl;

    public TeamsSquad() {
    }

    public TeamsSquad(String str, String str2, String str3) {
        this.playerName = str;
        this.playerImage = str2;
        this.playerUrl = str3;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerImage() {
        return this.playerImage;
    }

    public void setPlayerImage(String str) {
        this.playerImage = str;
    }

    public String getPlayerUrl() {
        return this.playerUrl;
    }

    public void setPlayerUrl(String str) {
        this.playerUrl = str;
    }
}