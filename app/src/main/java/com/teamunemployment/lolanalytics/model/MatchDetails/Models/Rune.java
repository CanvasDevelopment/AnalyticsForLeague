package com.teamunemployment.lolanalytics.model.MatchDetails.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jek40 on 18/09/2016.
 */
public class Rune {

    @SerializedName("runeId")
    @Expose
    private Integer runeId;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    /**
     *
     * @return
     * The runeId
     */
    public Integer getRuneId() {
        return runeId;
    }

    /**
     *
     * @param runeId
     * The runeId
     */
    public void setRuneId(Integer runeId) {
        this.runeId = runeId;
    }

    /**
     *
     * @return
     * The rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     * The rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
