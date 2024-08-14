package com.truecodes.worldcuptv.MainPlayers.Models;
public class BatsmanSummary {
    private String avg;
    private String hs;
    private String inngs;
    private String match;
    private String name;
    private String runs;

    public BatsmanSummary() {
    }

    public BatsmanSummary(String str, String str2, String str3, String str4, String str5, String str6) {
        this.name = str;
        this.match = str2;
        this.inngs = str3;
        this.runs = str4;
        this.hs = str5;
        this.avg = str6;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getMatch() {
        return this.match;
    }

    public void setMatch(String str) {
        this.match = str;
    }

    public String getInngs() {
        return this.inngs;
    }

    public void setInngs(String str) {
        this.inngs = str;
    }

    public String getRuns() {
        return this.runs;
    }

    public void setRuns(String str) {
        this.runs = str;
    }

    public String getHs() {
        return this.hs;
    }

    public void setHs(String str) {
        this.hs = str;
    }

    public String getAvg() {
        return this.avg;
    }

    public void setAvg(String str) {
        this.avg = str;
    }
}