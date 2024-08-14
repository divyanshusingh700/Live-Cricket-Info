package com.truecodes.worldcuptv.Models;


public class AsiaCupPointTable {
    private String lose;
    private String match;
    private String nrr;
    private String points;
    private String teamName;
    private String tied;
    private String win;

    public AsiaCupPointTable() {
    }

    public AsiaCupPointTable(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.teamName = str;
        this.match = str2;
        this.win = str3;
        this.lose = str4;
        this.tied = str5;
        this.points = str6;
        this.nrr = str7;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String str) {
        this.teamName = str;
    }

    public String getMatch() {
        return this.match;
    }

    public void setMatch(String str) {
        this.match = str;
    }

    public String getWin() {
        return this.win;
    }

    public void setWin(String str) {
        this.win = str;
    }

    public String getLose() {
        return this.lose;
    }

    public void setLose(String str) {
        this.lose = str;
    }

    public String getTied() {
        return this.tied;
    }

    public void setTied(String str) {
        this.tied = str;
    }

    public String getPoints() {
        return this.points;
    }

    public void setPoints(String str) {
        this.points = str;
    }

    public String getNrr() {
        return this.nrr;
    }

    public void setNrr(String str) {
        this.nrr = str;
    }
}