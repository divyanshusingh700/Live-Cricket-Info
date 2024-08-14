package com.truecodes.worldcuptv.Rankings;

public class TeamRanking {
    private String matches;
    private String points;
    private String position;
    private String ratting;
    private String teamImage;
    private String teamName;

    public TeamRanking() {
    }

    public TeamRanking(String str, String str2, String str3, String str4, String str5, String str6) {
        this.position = str;
        this.teamImage = str2;
        this.teamName = str3;
        this.matches = str4;
        this.points = str5;
        this.ratting = str6;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public String getTeamImage() {
        return this.teamImage;
    }

    public void setTeamImage(String str) {
        this.teamImage = str;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String str) {
        this.teamName = str;
    }

    public String getMatches() {
        return this.matches;
    }

    public void setMatches(String str) {
        this.matches = str;
    }

    public String getPoints() {
        return this.points;
    }

    public void setPoints(String str) {
        this.points = str;
    }

    public String getRatting() {
        return this.ratting;
    }

    public void setRatting(String str) {
        this.ratting = str;
    }
}