package com.truecodes.worldcuptv.CricketSeries.Models;


public class CricketSeries {
    private String seriesDate;
    private String seriesDetails;
    private String seriesName;

    public CricketSeries() {
    }

    public CricketSeries(String str, String str2, String str3) {
        this.seriesName = str;
        this.seriesDate = str2;
        this.seriesDetails = str3;
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public void setSeriesName(String str) {
        this.seriesName = str;
    }

    public String getSeriesDate() {
        return this.seriesDate;
    }

    public void setSeriesDate(String str) {
        this.seriesDate = str;
    }

    public String getSeriesDetails() {
        return this.seriesDetails;
    }

    public void setSeriesDetails(String str) {
        this.seriesDetails = str;
    }
}