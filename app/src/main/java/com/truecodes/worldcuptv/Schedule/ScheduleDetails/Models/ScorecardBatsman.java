package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models;


public class ScorecardBatsman {
    private String playerB;
    private String playerF;
    private String playerName;
    private String playerR;
    private String playerS;
    private String playerSR;
    private String playerStatus;

    public ScorecardBatsman() {
    }

    public ScorecardBatsman(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.playerName = str;
        this.playerStatus = str2;
        this.playerR = str3;
        this.playerB = str4;
        this.playerF = str5;
        this.playerS = str6;
        this.playerSR = str7;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
    }

    public String getPlayerStatus() {
        return this.playerStatus;
    }

    public void setPlayerStatus(String str) {
        this.playerStatus = str;
    }

    public String getPlayerR() {
        return this.playerR;
    }

    public void setPlayerR(String str) {
        this.playerR = str;
    }

    public String getPlayerB() {
        return this.playerB;
    }

    public void setPlayerB(String str) {
        this.playerB = str;
    }

    public String getPlayerF() {
        return this.playerF;
    }

    public void setPlayerF(String str) {
        this.playerF = str;
    }

    public String getPlayerS() {
        return this.playerS;
    }

    public void setPlayerS(String str) {
        this.playerS = str;
    }

    public String getPlayerSR() {
        return this.playerSR;
    }

    public void setPlayerSR(String str) {
        this.playerSR = str;
    }
}