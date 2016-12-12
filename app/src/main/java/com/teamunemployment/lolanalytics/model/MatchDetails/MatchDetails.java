package com.teamunemployment.lolanalytics.model.MatchDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.teamunemployment.lolanalytics.model.MatchDetails.Models.Mastery;
import com.teamunemployment.lolanalytics.model.MatchDetails.Models.Rune;
import com.teamunemployment.lolanalytics.model.MatchDetails.Models.Stats;
import com.teamunemployment.lolanalytics.model.MatchDetails.Models.Timeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jek40 on 18/09/2016.
 */
public class MatchDetails {

    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("spell1Id")
    @Expose
    private Integer spell1Id;
    @SerializedName("spell2Id")
    @Expose
    private Integer spell2Id;
    @SerializedName("championId")
    @Expose
    private Integer championId;
    @SerializedName("highestAchievedSeasonTier")
    @Expose
    private String highestAchievedSeasonTier;
    @SerializedName("timeline")
    @Expose
    private Timeline timeline;
    @SerializedName("masteries")
    @Expose
    private List<Mastery> masteries = new ArrayList<Mastery>();
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("participantId")
    @Expose
    private Integer participantId;
    @SerializedName("runes")
    @Expose
    private List<Rune> runes = new ArrayList<Rune>();

    /**
     *
     * @return
     * The teamId
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     *
     * @param teamId
     * The teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     *
     * @return
     * The spell1Id
     */
    public Integer getSpell1Id() {
        return spell1Id;
    }

    /**
     *
     * @param spell1Id
     * The spell1Id
     */
    public void setSpell1Id(Integer spell1Id) {
        this.spell1Id = spell1Id;
    }

    /**
     *
     * @return
     * The spell2Id
     */
    public Integer getSpell2Id() {
        return spell2Id;
    }

    /**
     *
     * @param spell2Id
     * The spell2Id
     */
    public void setSpell2Id(Integer spell2Id) {
        this.spell2Id = spell2Id;
    }

    /**
     *
     * @return
     * The championId
     */
    public Integer getChampionId() {
        return championId;
    }

    /**
     *
     * @param championId
     * The championId
     */
    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    /**
     *
     * @return
     * The highestAchievedSeasonTier
     */
    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    /**
     *
     * @param highestAchievedSeasonTier
     * The highestAchievedSeasonTier
     */
    public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    /**
     *
     * @return
     * The timeline
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     *
     * @param timeline
     * The timeline
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     *
     * @return
     * The masteries
     */
    public List<Mastery> getMasteries() {
        return masteries;
    }

    /**
     *
     * @param masteries
     * The masteries
     */
    public void setMasteries(List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     *
     * @return
     * The stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     *
     * @param stats
     * The stats
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     *
     * @return
     * The participantId
     */
    public Integer getParticipantId() {
        return participantId;
    }

    /**
     *
     * @param participantId
     * The participantId
     */
    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    /**
     *
     * @return
     * The runes
     */
    public List<Rune> getRunes() {
        return runes;
    }

    /**
     *
     * @param runes
     * The runes
     */
    public void setRunes(List<Rune> runes) {
        this.runes = runes;
    }

}
