package com.teamunemployment.lolanalytics.model.MatchDetails.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Josiah Kendall.
 */
public class CreepsPerMinDeltas {

    @SerializedName("zeroToTen")
    @Expose
    private Double zeroToTen;
    @SerializedName("tenToTwenty")
    @Expose
    private Double tenToTwenty;
    @SerializedName("twentyToThirty")
    @Expose
    private Double twentyToThirty;
    @SerializedName("thirtyToEnd")
    @Expose
    private Double thirtyToEnd;

    /**
     *
     * @return
     * The zeroToTen
     */
    public Double getZeroToTen() {
        return zeroToTen;
    }

    /**
     *
     * @param zeroToTen
     * The zeroToTen
     */
    public void setZeroToTen(Double zeroToTen) {
        this.zeroToTen = zeroToTen;
    }

    /**
     *
     * @return
     * The tenToTwenty
     */
    public Double getTenToTwenty() {
        return tenToTwenty;
    }

    /**
     *
     * @param tenToTwenty
     * The tenToTwenty
     */
    public void setTenToTwenty(Double tenToTwenty) {
        this.tenToTwenty = tenToTwenty;
    }

    /**
     *
     * @return
     * The twentyToThirty
     */
    public Double getTwentyToThirty() {
        return twentyToThirty;
    }

    /**
     *
     * @param twentyToThirty
     * The twentyToThirty
     */
    public void setTwentyToThirty(Double twentyToThirty) {
        this.twentyToThirty = twentyToThirty;
    }

    /**
     *
     * @return
     * The thirtyToEnd
     */
    public Double getThirtyToEnd() {
        return thirtyToEnd;
    }

    /**
     *
     * @param thirtyToEnd
     * The thirtyToEnd
     */
    public void setThirtyToEnd(Double thirtyToEnd) {
        this.thirtyToEnd = thirtyToEnd;
    }

}
