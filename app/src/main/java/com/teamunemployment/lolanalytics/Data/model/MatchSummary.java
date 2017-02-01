package com.teamunemployment.lolanalytics.Data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Josiah Kendall
 */
public class MatchSummary extends RealmObject {

    @PrimaryKey
    public long id;
    public double value;
    public long summonerId;
    public int role;

    public void setRole(int role) {
        this.role = role;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public long getSummonerId() {
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
