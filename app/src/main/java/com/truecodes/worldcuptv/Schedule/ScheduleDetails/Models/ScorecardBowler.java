package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models;

public class ScorecardBowler {
    private String playerB;
    private String playerF;
    private String playerName;
    private String playerR;
    private String playerS;
    private String playerSR;

    public ScorecardBowler() {
    }

    public ScorecardBowler(String str, String str2, String str3, String str4, String str5, String str6) {
        this.playerName = str;
        this.playerR = str2;
        this.playerB = str3;
        this.playerF = str4;
        this.playerS = str5;
        this.playerSR = str6;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String str) {
        this.playerName = str;
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