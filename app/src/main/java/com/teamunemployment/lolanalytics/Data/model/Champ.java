package com.teamunemployment.lolanalytics.Data.model;

/**
 * Created by Josiah Kendall
 */

public class Champ {
    private String champName;
    private String champUrl;
    private int champId;

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public void setChampUrl(String champUrl) {
        this.champUrl = champUrl;
    }

    public String getChampName() {
        return champName;
    }

    public String getChampUrl() {
        return champUrl;
    }

    public void setChampResId(int resId) {
        this.champId = resId;
    }

    public int getChampId() {
        return champId;
    }

    @Override
    public String toString() {
        return champName;
    }

    public void setChampId(int champId) {
        this.champId = champId;
    }
}
