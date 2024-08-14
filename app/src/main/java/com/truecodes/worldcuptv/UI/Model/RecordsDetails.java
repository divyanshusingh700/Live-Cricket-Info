package com.truecodes.worldcuptv.UI.Model;




public class RecordsDetails {
    private String name;
    private String score;
    private String secondScore;

    public RecordsDetails() {
    }

    public RecordsDetails(String str, String str2, String str3) {
        this.name = str;
        this.score = str2;
        this.secondScore = str3;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public String getSecondScore() {
        return this.secondScore;
    }

    public void setSecondScore(String str) {
        this.secondScore = str;
    }
}