package com.teamunemployment.lolanalytics.model.MatchDetails.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jek40 on 18/09/2016.
 */
public class Timeline {

    @SerializedName("creepsPerMinDeltas")
    @Expose
    private CreepsPerMinDeltas creepsPerMinDeltas;
    @SerializedName("xpPerMinDeltas")
    @Expose
    private XpPerMinDeltas xpPerMinDeltas;
    @SerializedName("goldPerMinDeltas")
    @Expose
    private GoldPerMinDeltas goldPerMinDeltas;
    @SerializedName("csDiffPerMinDeltas")
    @Expose
    private CsDiffPerMinDeltas csDiffPerMinDeltas;
    @SerializedName("xpDiffPerMinDeltas")
    @Expose
    private XpDiffPerMinDeltas xpDiffPerMinDeltas;
    @SerializedName("damageTakenPerMinDeltas")
    @Expose
    private DamageTakenPerMinDeltas damageTakenPerMinDeltas;
    @SerializedName("damageTakenDiffPerMinDeltas")
    @Expose
    private DamageTakenDiffPerMinDeltas damageTakenDiffPerMinDeltas;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("lane")
    @Expose
    private String lane;

    /**
     *
     * @return
     * The creepsPerMinDeltas
     */
    public CreepsPerMinDeltas getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    /**
     *
     * @param creepsPerMinDeltas
     * The creepsPerMinDeltas
     */
    public void setCreepsPerMinDeltas(CreepsPerMinDeltas creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    /**
     *
     * @return
     * The xpPerMinDeltas
     */
    public XpPerMinDeltas getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    /**
     *
     * @param xpPerMinDeltas
     * The xpPerMinDeltas
     */
    public void setXpPerMinDeltas(XpPerMinDeltas xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }

    /**
     *
     * @return
     * The goldPerMinDeltas
     */
    public GoldPerMinDeltas getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    /**
     *
     * @param goldPerMinDeltas
     * The goldPerMinDeltas
     */
    public void setGoldPerMinDeltas(GoldPerMinDeltas goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    /**
     *
     * @return
     * The csDiffPerMinDeltas
     */
    public CsDiffPerMinDeltas getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    /**
     *
     * @param csDiffPerMinDeltas
     * The csDiffPerMinDeltas
     */
    public void setCsDiffPerMinDeltas(CsDiffPerMinDeltas csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    /**
     *
     * @return
     * The xpDiffPerMinDeltas
     */
    public XpDiffPerMinDeltas getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    /**
     *
     * @param xpDiffPerMinDeltas
     * The xpDiffPerMinDeltas
     */
    public void setXpDiffPerMinDeltas(XpDiffPerMinDeltas xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    /**
     *
     * @return
     * The damageTakenPerMinDeltas
     */
    public DamageTakenPerMinDeltas getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    /**
     *
     * @param damageTakenPerMinDeltas
     * The damageTakenPerMinDeltas
     */
    public void setDamageTakenPerMinDeltas(DamageTakenPerMinDeltas damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    /**
     *
     * @return
     * The damageTakenDiffPerMinDeltas
     */
    public DamageTakenDiffPerMinDeltas getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    /**
     *
     * @param damageTakenDiffPerMinDeltas
     * The damageTakenDiffPerMinDeltas
     */
    public void setDamageTakenDiffPerMinDeltas(DamageTakenDiffPerMinDeltas damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     * The lane
     */
    public String getLane() {
        return lane;
    }

    /**
     *
     * @param lane
     * The lane
     */
    public void setLane(String lane) {
        this.lane = lane;
    }

}
