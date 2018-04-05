package com.SDP.Models;


public class ExperienceObject {

    private int level;
    private String title;
    private int totalExp;
    private int remainingExp;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    public int getRemainingExp() {
        return remainingExp;
    }

    public void setRemainingExp(int remainingExp) {
        this.remainingExp = remainingExp;
    }
}
