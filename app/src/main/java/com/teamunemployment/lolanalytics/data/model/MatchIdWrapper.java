package com.teamunemployment.lolanalytics.data.model;

/**
 * @author Josiah Kendall
 */

public class MatchIdWrapper {

    private long matchId;
    private String summonerId;
    private int role;

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getMatchId() {
        return matchId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

}
