package com.teamunemployment.lolanalytics.model.MatchDetails.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jek40 on 18/09/2016.
 */
public class Stats {

    @SerializedName("winner")
    @Expose
    private Boolean winner;
    @SerializedName("champLevel")
    @Expose
    private Integer champLevel;
    @SerializedName("item0")
    @Expose
    private Integer item0;
    @SerializedName("item1")
    @Expose
    private Integer item1;
    @SerializedName("item2")
    @Expose
    private Integer item2;
    @SerializedName("item3")
    @Expose
    private Integer item3;
    @SerializedName("item4")
    @Expose
    private Integer item4;
    @SerializedName("item5")
    @Expose
    private Integer item5;
    @SerializedName("item6")
    @Expose
    private Integer item6;
    @SerializedName("kills")
    @Expose
    private Integer kills;
    @SerializedName("doubleKills")
    @Expose
    private Integer doubleKills;
    @SerializedName("tripleKills")
    @Expose
    private Integer tripleKills;
    @SerializedName("quadraKills")
    @Expose
    private Integer quadraKills;
    @SerializedName("pentaKills")
    @Expose
    private Integer pentaKills;
    @SerializedName("unrealKills")
    @Expose
    private Integer unrealKills;
    @SerializedName("largestKillingSpree")
    @Expose
    private Integer largestKillingSpree;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("assists")
    @Expose
    private Integer assists;
    @SerializedName("totalDamageDealt")
    @Expose
    private Integer totalDamageDealt;
    @SerializedName("totalDamageDealtToChampions")
    @Expose
    private Integer totalDamageDealtToChampions;
    @SerializedName("totalDamageTaken")
    @Expose
    private Integer totalDamageTaken;
    @SerializedName("largestCriticalStrike")
    @Expose
    private Integer largestCriticalStrike;
    @SerializedName("totalHeal")
    @Expose
    private Integer totalHeal;
    @SerializedName("minionsKilled")
    @Expose
    private Integer minionsKilled;
    @SerializedName("neutralMinionsKilled")
    @Expose
    private Integer neutralMinionsKilled;
    @SerializedName("neutralMinionsKilledTeamJungle")
    @Expose
    private Integer neutralMinionsKilledTeamJungle;
    @SerializedName("neutralMinionsKilledEnemyJungle")
    @Expose
    private Integer neutralMinionsKilledEnemyJungle;
    @SerializedName("goldEarned")
    @Expose
    private Integer goldEarned;
    @SerializedName("goldSpent")
    @Expose
    private Integer goldSpent;
    @SerializedName("combatPlayerScore")
    @Expose
    private Integer combatPlayerScore;
    @SerializedName("objectivePlayerScore")
    @Expose
    private Integer objectivePlayerScore;
    @SerializedName("totalPlayerScore")
    @Expose
    private Integer totalPlayerScore;
    @SerializedName("totalScoreRank")
    @Expose
    private Integer totalScoreRank;
    @SerializedName("magicDamageDealtToChampions")
    @Expose
    private Integer magicDamageDealtToChampions;
    @SerializedName("physicalDamageDealtToChampions")
    @Expose
    private Integer physicalDamageDealtToChampions;
    @SerializedName("trueDamageDealtToChampions")
    @Expose
    private Integer trueDamageDealtToChampions;
    @SerializedName("visionWardsBoughtInGame")
    @Expose
    private Integer visionWardsBoughtInGame;
    @SerializedName("sightWardsBoughtInGame")
    @Expose
    private Integer sightWardsBoughtInGame;
    @SerializedName("magicDamageDealt")
    @Expose
    private Integer magicDamageDealt;
    @SerializedName("physicalDamageDealt")
    @Expose
    private Integer physicalDamageDealt;
    @SerializedName("trueDamageDealt")
    @Expose
    private Integer trueDamageDealt;
    @SerializedName("magicDamageTaken")
    @Expose
    private Integer magicDamageTaken;
    @SerializedName("physicalDamageTaken")
    @Expose
    private Integer physicalDamageTaken;
    @SerializedName("trueDamageTaken")
    @Expose
    private Integer trueDamageTaken;
    @SerializedName("firstBloodKill")
    @Expose
    private Boolean firstBloodKill;
    @SerializedName("firstBloodAssist")
    @Expose
    private Boolean firstBloodAssist;
    @SerializedName("firstTowerKill")
    @Expose
    private Boolean firstTowerKill;
    @SerializedName("firstTowerAssist")
    @Expose
    private Boolean firstTowerAssist;
    @SerializedName("firstInhibitorKill")
    @Expose
    private Boolean firstInhibitorKill;
    @SerializedName("firstInhibitorAssist")
    @Expose
    private Boolean firstInhibitorAssist;
    @SerializedName("inhibitorKills")
    @Expose
    private Integer inhibitorKills;
    @SerializedName("towerKills")
    @Expose
    private Integer towerKills;
    @SerializedName("wardsPlaced")
    @Expose
    private Integer wardsPlaced;
    @SerializedName("wardsKilled")
    @Expose
    private Integer wardsKilled;
    @SerializedName("largestMultiKill")
    @Expose
    private Integer largestMultiKill;
    @SerializedName("killingSprees")
    @Expose
    private Integer killingSprees;
    @SerializedName("totalUnitsHealed")
    @Expose
    private Integer totalUnitsHealed;
    @SerializedName("totalTimeCrowdControlDealt")
    @Expose
    private Integer totalTimeCrowdControlDealt;

    /**
     *
     * @return
     * The winner
     */
    public Boolean getWinner() {
        return winner;
    }

    /**
     *
     * @param winner
     * The winner
     */
    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    /**
     *
     * @return
     * The champLevel
     */
    public Integer getChampLevel() {
        return champLevel;
    }

    /**
     *
     * @param champLevel
     * The champLevel
     */
    public void setChampLevel(Integer champLevel) {
        this.champLevel = champLevel;
    }

    /**
     *
     * @return
     * The item0
     */
    public Integer getItem0() {
        return item0;
    }

    /**
     *
     * @param item0
     * The item0
     */
    public void setItem0(Integer item0) {
        this.item0 = item0;
    }

    /**
     *
     * @return
     * The item1
     */
    public Integer getItem1() {
        return item1;
    }

    /**
     *
     * @param item1
     * The item1
     */
    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    /**
     *
     * @return
     * The item2
     */
    public Integer getItem2() {
        return item2;
    }

    /**
     *
     * @param item2
     * The item2
     */
    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    /**
     *
     * @return
     * The item3
     */
    public Integer getItem3() {
        return item3;
    }

    /**
     *
     * @param item3
     * The item3
     */
    public void setItem3(Integer item3) {
        this.item3 = item3;
    }

    /**
     *
     * @return
     * The item4
     */
    public Integer getItem4() {
        return item4;
    }

    /**
     *
     * @param item4
     * The item4
     */
    public void setItem4(Integer item4) {
        this.item4 = item4;
    }

    /**
     *
     * @return
     * The item5
     */
    public Integer getItem5() {
        return item5;
    }

    /**
     *
     * @param item5
     * The item5
     */
    public void setItem5(Integer item5) {
        this.item5 = item5;
    }

    /**
     *
     * @return
     * The item6
     */
    public Integer getItem6() {
        return item6;
    }

    /**
     *
     * @param item6
     * The item6
     */
    public void setItem6(Integer item6) {
        this.item6 = item6;
    }

    /**
     *
     * @return
     * The kills
     */
    public Integer getKills() {
        return kills;
    }

    /**
     *
     * @param kills
     * The kills
     */
    public void setKills(Integer kills) {
        this.kills = kills;
    }

    /**
     *
     * @return
     * The doubleKills
     */
    public Integer getDoubleKills() {
        return doubleKills;
    }

    /**
     *
     * @param doubleKills
     * The doubleKills
     */
    public void setDoubleKills(Integer doubleKills) {
        this.doubleKills = doubleKills;
    }

    /**
     *
     * @return
     * The tripleKills
     */
    public Integer getTripleKills() {
        return tripleKills;
    }

    /**
     *
     * @param tripleKills
     * The tripleKills
     */
    public void setTripleKills(Integer tripleKills) {
        this.tripleKills = tripleKills;
    }

    /**
     *
     * @return
     * The quadraKills
     */
    public Integer getQuadraKills() {
        return quadraKills;
    }

    /**
     *
     * @param quadraKills
     * The quadraKills
     */
    public void setQuadraKills(Integer quadraKills) {
        this.quadraKills = quadraKills;
    }

    /**
     *
     * @return
     * The pentaKills
     */
    public Integer getPentaKills() {
        return pentaKills;
    }

    /**
     *
     * @param pentaKills
     * The pentaKills
     */
    public void setPentaKills(Integer pentaKills) {
        this.pentaKills = pentaKills;
    }

    /**
     *
     * @return
     * The unrealKills
     */
    public Integer getUnrealKills() {
        return unrealKills;
    }

    /**
     *
     * @param unrealKills
     * The unrealKills
     */
    public void setUnrealKills(Integer unrealKills) {
        this.unrealKills = unrealKills;
    }

    /**
     *
     * @return
     * The largestKillingSpree
     */
    public Integer getLargestKillingSpree() {
        return largestKillingSpree;
    }

    /**
     *
     * @param largestKillingSpree
     * The largestKillingSpree
     */
    public void setLargestKillingSpree(Integer largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    /**
     *
     * @return
     * The deaths
     */
    public Integer getDeaths() {
        return deaths;
    }

    /**
     *
     * @param deaths
     * The deaths
     */
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    /**
     *
     * @return
     * The assists
     */
    public Integer getAssists() {
        return assists;
    }

    /**
     *
     * @param assists
     * The assists
     */
    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    /**
     *
     * @return
     * The totalDamageDealt
     */
    public Integer getTotalDamageDealt() {
        return totalDamageDealt;
    }

    /**
     *
     * @param totalDamageDealt
     * The totalDamageDealt
     */
    public void setTotalDamageDealt(Integer totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    /**
     *
     * @return
     * The totalDamageDealtToChampions
     */
    public Integer getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    /**
     *
     * @param totalDamageDealtToChampions
     * The totalDamageDealtToChampions
     */
    public void setTotalDamageDealtToChampions(Integer totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    /**
     *
     * @return
     * The totalDamageTaken
     */
    public Integer getTotalDamageTaken() {
        return totalDamageTaken;
    }

    /**
     *
     * @param totalDamageTaken
     * The totalDamageTaken
     */
    public void setTotalDamageTaken(Integer totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    /**
     *
     * @return
     * The largestCriticalStrike
     */
    public Integer getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    /**
     *
     * @param largestCriticalStrike
     * The largestCriticalStrike
     */
    public void setLargestCriticalStrike(Integer largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    /**
     *
     * @return
     * The totalHeal
     */
    public Integer getTotalHeal() {
        return totalHeal;
    }

    /**
     *
     * @param totalHeal
     * The totalHeal
     */
    public void setTotalHeal(Integer totalHeal) {
        this.totalHeal = totalHeal;
    }

    /**
     *
     * @return
     * The minionsKilled
     */
    public Integer getMinionsKilled() {
        return minionsKilled;
    }

    /**
     *
     * @param minionsKilled
     * The minionsKilled
     */
    public void setMinionsKilled(Integer minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    /**
     *
     * @return
     * The neutralMinionsKilled
     */
    public Integer getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    /**
     *
     * @param neutralMinionsKilled
     * The neutralMinionsKilled
     */
    public void setNeutralMinionsKilled(Integer neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    /**
     *
     * @return
     * The neutralMinionsKilledTeamJungle
     */
    public Integer getNeutralMinionsKilledTeamJungle() {
        return neutralMinionsKilledTeamJungle;
    }

    /**
     *
     * @param neutralMinionsKilledTeamJungle
     * The neutralMinionsKilledTeamJungle
     */
    public void setNeutralMinionsKilledTeamJungle(Integer neutralMinionsKilledTeamJungle) {
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
    }

    /**
     *
     * @return
     * The neutralMinionsKilledEnemyJungle
     */
    public Integer getNeutralMinionsKilledEnemyJungle() {
        return neutralMinionsKilledEnemyJungle;
    }

    /**
     *
     * @param neutralMinionsKilledEnemyJungle
     * The neutralMinionsKilledEnemyJungle
     */
    public void setNeutralMinionsKilledEnemyJungle(Integer neutralMinionsKilledEnemyJungle) {
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
    }

    /**
     *
     * @return
     * The goldEarned
     */
    public Integer getGoldEarned() {
        return goldEarned;
    }

    /**
     *
     * @param goldEarned
     * The goldEarned
     */
    public void setGoldEarned(Integer goldEarned) {
        this.goldEarned = goldEarned;
    }

    /**
     *
     * @return
     * The goldSpent
     */
    public Integer getGoldSpent() {
        return goldSpent;
    }

    /**
     *
     * @param goldSpent
     * The goldSpent
     */
    public void setGoldSpent(Integer goldSpent) {
        this.goldSpent = goldSpent;
    }

    /**
     *
     * @return
     * The combatPlayerScore
     */
    public Integer getCombatPlayerScore() {
        return combatPlayerScore;
    }

    /**
     *
     * @param combatPlayerScore
     * The combatPlayerScore
     */
    public void setCombatPlayerScore(Integer combatPlayerScore) {
        this.combatPlayerScore = combatPlayerScore;
    }

    /**
     *
     * @return
     * The objectivePlayerScore
     */
    public Integer getObjectivePlayerScore() {
        return objectivePlayerScore;
    }

    /**
     *
     * @param objectivePlayerScore
     * The objectivePlayerScore
     */
    public void setObjectivePlayerScore(Integer objectivePlayerScore) {
        this.objectivePlayerScore = objectivePlayerScore;
    }

    /**
     *
     * @return
     * The totalPlayerScore
     */
    public Integer getTotalPlayerScore() {
        return totalPlayerScore;
    }

    /**
     *
     * @param totalPlayerScore
     * The totalPlayerScore
     */
    public void setTotalPlayerScore(Integer totalPlayerScore) {
        this.totalPlayerScore = totalPlayerScore;
    }

    /**
     *
     * @return
     * The totalScoreRank
     */
    public Integer getTotalScoreRank() {
        return totalScoreRank;
    }

    /**
     *
     * @param totalScoreRank
     * The totalScoreRank
     */
    public void setTotalScoreRank(Integer totalScoreRank) {
        this.totalScoreRank = totalScoreRank;
    }

    /**
     *
     * @return
     * The magicDamageDealtToChampions
     */
    public Integer getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    /**
     *
     * @param magicDamageDealtToChampions
     * The magicDamageDealtToChampions
     */
    public void setMagicDamageDealtToChampions(Integer magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    /**
     *
     * @return
     * The physicalDamageDealtToChampions
     */
    public Integer getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    /**
     *
     * @param physicalDamageDealtToChampions
     * The physicalDamageDealtToChampions
     */
    public void setPhysicalDamageDealtToChampions(Integer physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    /**
     *
     * @return
     * The trueDamageDealtToChampions
     */
    public Integer getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    /**
     *
     * @param trueDamageDealtToChampions
     * The trueDamageDealtToChampions
     */
    public void setTrueDamageDealtToChampions(Integer trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    /**
     *
     * @return
     * The visionWardsBoughtInGame
     */
    public Integer getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    /**
     *
     * @param visionWardsBoughtInGame
     * The visionWardsBoughtInGame
     */
    public void setVisionWardsBoughtInGame(Integer visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    /**
     *
     * @return
     * The sightWardsBoughtInGame
     */
    public Integer getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    /**
     *
     * @param sightWardsBoughtInGame
     * The sightWardsBoughtInGame
     */
    public void setSightWardsBoughtInGame(Integer sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    /**
     *
     * @return
     * The magicDamageDealt
     */
    public Integer getMagicDamageDealt() {
        return magicDamageDealt;
    }

    /**
     *
     * @param magicDamageDealt
     * The magicDamageDealt
     */
    public void setMagicDamageDealt(Integer magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    /**
     *
     * @return
     * The physicalDamageDealt
     */
    public Integer getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    /**
     *
     * @param physicalDamageDealt
     * The physicalDamageDealt
     */
    public void setPhysicalDamageDealt(Integer physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    /**
     *
     * @return
     * The trueDamageDealt
     */
    public Integer getTrueDamageDealt() {
        return trueDamageDealt;
    }

    /**
     *
     * @param trueDamageDealt
     * The trueDamageDealt
     */
    public void setTrueDamageDealt(Integer trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    /**
     *
     * @return
     * The magicDamageTaken
     */
    public Integer getMagicDamageTaken() {
        return magicDamageTaken;
    }

    /**
     *
     * @param magicDamageTaken
     * The magicDamageTaken
     */
    public void setMagicDamageTaken(Integer magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    /**
     *
     * @return
     * The physicalDamageTaken
     */
    public Integer getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    /**
     *
     * @param physicalDamageTaken
     * The physicalDamageTaken
     */
    public void setPhysicalDamageTaken(Integer physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    /**
     *
     * @return
     * The trueDamageTaken
     */
    public Integer getTrueDamageTaken() {
        return trueDamageTaken;
    }

    /**
     *
     * @param trueDamageTaken
     * The trueDamageTaken
     */
    public void setTrueDamageTaken(Integer trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    /**
     *
     * @return
     * The firstBloodKill
     */
    public Boolean getFirstBloodKill() {
        return firstBloodKill;
    }

    /**
     *
     * @param firstBloodKill
     * The firstBloodKill
     */
    public void setFirstBloodKill(Boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    /**
     *
     * @return
     * The firstBloodAssist
     */
    public Boolean getFirstBloodAssist() {
        return firstBloodAssist;
    }

    /**
     *
     * @param firstBloodAssist
     * The firstBloodAssist
     */
    public void setFirstBloodAssist(Boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    /**
     *
     * @return
     * The firstTowerKill
     */
    public Boolean getFirstTowerKill() {
        return firstTowerKill;
    }

    /**
     *
     * @param firstTowerKill
     * The firstTowerKill
     */
    public void setFirstTowerKill(Boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    /**
     *
     * @return
     * The firstTowerAssist
     */
    public Boolean getFirstTowerAssist() {
        return firstTowerAssist;
    }

    /**
     *
     * @param firstTowerAssist
     * The firstTowerAssist
     */
    public void setFirstTowerAssist(Boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    /**
     *
     * @return
     * The firstInhibitorKill
     */
    public Boolean getFirstInhibitorKill() {
        return firstInhibitorKill;
    }

    /**
     *
     * @param firstInhibitorKill
     * The firstInhibitorKill
     */
    public void setFirstInhibitorKill(Boolean firstInhibitorKill) {
        this.firstInhibitorKill = firstInhibitorKill;
    }

    /**
     *
     * @return
     * The firstInhibitorAssist
     */
    public Boolean getFirstInhibitorAssist() {
        return firstInhibitorAssist;
    }

    /**
     *
     * @param firstInhibitorAssist
     * The firstInhibitorAssist
     */
    public void setFirstInhibitorAssist(Boolean firstInhibitorAssist) {
        this.firstInhibitorAssist = firstInhibitorAssist;
    }

    /**
     *
     * @return
     * The inhibitorKills
     */
    public Integer getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     *
     * @param inhibitorKills
     * The inhibitorKills
     */
    public void setInhibitorKills(Integer inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     *
     * @return
     * The towerKills
     */
    public Integer getTowerKills() {
        return towerKills;
    }

    /**
     *
     * @param towerKills
     * The towerKills
     */
    public void setTowerKills(Integer towerKills) {
        this.towerKills = towerKills;
    }

    /**
     *
     * @return
     * The wardsPlaced
     */
    public Integer getWardsPlaced() {
        return wardsPlaced;
    }

    /**
     *
     * @param wardsPlaced
     * The wardsPlaced
     */
    public void setWardsPlaced(Integer wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    /**
     *
     * @return
     * The wardsKilled
     */
    public Integer getWardsKilled() {
        return wardsKilled;
    }

    /**
     *
     * @param wardsKilled
     * The wardsKilled
     */
    public void setWardsKilled(Integer wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    /**
     *
     * @return
     * The largestMultiKill
     */
    public Integer getLargestMultiKill() {
        return largestMultiKill;
    }

    /**
     *
     * @param largestMultiKill
     * The largestMultiKill
     */
    public void setLargestMultiKill(Integer largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    /**
     *
     * @return
     * The killingSprees
     */
    public Integer getKillingSprees() {
        return killingSprees;
    }

    /**
     *
     * @param killingSprees
     * The killingSprees
     */
    public void setKillingSprees(Integer killingSprees) {
        this.killingSprees = killingSprees;
    }

    /**
     *
     * @return
     * The totalUnitsHealed
     */
    public Integer getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    /**
     *
     * @param totalUnitsHealed
     * The totalUnitsHealed
     */
    public void setTotalUnitsHealed(Integer totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    /**
     *
     * @return
     * The totalTimeCrowdControlDealt
     */
    public Integer getTotalTimeCrowdControlDealt() {
        return totalTimeCrowdControlDealt;
    }

    /**
     *
     * @param totalTimeCrowdControlDealt
     * The totalTimeCrowdControlDealt
     */
    public void setTotalTimeCrowdControlDealt(Integer totalTimeCrowdControlDealt) {
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
    }

}
