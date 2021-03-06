package com.teamunemployment.lolanalytics.data.model;

/**
 * @author Josiah Kendall
 */
public class MatchSummary {

    public long id;
    public double value;
    public String summonerId;
    public int role;

    public void setRole(int role) {
        this.role = role;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public int getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public long getId() {
        return id;
    }

}
