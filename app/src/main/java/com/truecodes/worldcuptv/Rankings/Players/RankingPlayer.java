package com.truecodes.worldcuptv.Rankings.Players;

public class RankingPlayer {
    private String position;
    private String rating;
    private String teamImage;
    private String teamName;

    public RankingPlayer() {
    }

    public RankingPlayer(String position, String teamName, String teamImage, String rating) {
        this.position = position;
        this.teamName = teamName;
        this.teamImage = teamImage;
        this.rating = rating;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String positionn) {position = positionn;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String str) {
        teamName = str;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String str) {
        teamImage = str;
    }

    public String getRatting() {
        return rating;
    }

    public void setRatting(String str) {
        rating = str;
    }
}