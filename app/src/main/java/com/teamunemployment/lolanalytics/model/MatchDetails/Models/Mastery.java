package com.teamunemployment.lolanalytics.model.MatchDetails.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jek40 on 18/09/2016.
 */
public class Mastery {

    @SerializedName("masteryId")
    @Expose
    private Integer masteryId;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    /**
     *
     * @return
     * The masteryId
     */
    public Integer getMasteryId() {
        return masteryId;
    }

    /**
     *
     * @param masteryId
     * The masteryId
     */
    public void setMasteryId(Integer masteryId) {
        this.masteryId = masteryId;
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
