package com.truecodes.worldcuptv.TeamAndPlayer;


public class Players {
    String playerImage;
    String playerName;

    public Players() {
    }

    public Players(String str, String str2) {
        this.playerName = str;
        this.playerImage = str2;
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
}