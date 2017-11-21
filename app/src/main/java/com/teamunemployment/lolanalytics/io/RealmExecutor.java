package com.teamunemployment.lolanalytics.io;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.Realm.RealmMigrator;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab.Model.Entry;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatPoint;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * @author Josiah Kendall
 *
 * This is our access to the Realm NoSQL database that is used instead of sqlite. For more information
 * on realm see https://realm.io/.
 *
 * Basically acts a simplified proxy to realm with specific methods for the data types required.
 */
public class RealmExecutor {

    private Context context;
    private static Realm realmInstance;

    public static Realm GetRealmInstance() {

            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                    .schemaVersion(4)
                    .migration(new RealmMigrator())
                    .build();
            return Realm.getInstance(realmConfiguration);
    }

    public static Realm GetRealmInstance(RealmConfiguration configuration) {
        return  Realm.getInstance(configuration);
    }

    public RealmExecutor(Context context) {
        this.context = context;
    }

    /**
     * Fetch a single data object for a card based on id that is assigned to that specific card on the
     * server.
     * @param id The id of the card data that we want
     * @return The required data.
     */
    public CardData GetCardDataByCardId(Realm realm, int id) {
        return findSingleObjectUsingRealm(realm, id);
    }

    /**
     * Fetch a single Match History card by its Id.
     * @param realm
     * @param id
     * @return
     */
    public MatchHistoryCardData GetSingleMatchHistoryCard(Realm realm, long id) {
        return findSingleMatchDetail(realm, id);
    }

    // Private version for fetching a single object by id.
    private CardData findSingleObjectUsingRealm(Realm realm, long id) {
        return realm.where(CardData.class).equalTo("id", id).findFirst();
    }

    // Private version for fetching a single object by id.
    private MatchSummary findSingleMatchSummary(Realm realm, long id) {
        return realm.where(MatchSummary.class).equalTo("id", id).findFirst();
    }

    // Private version for fetching a single object by id.
    private StatPoint findSingleStatPoint(Realm realm, long id) {
        return realm.where(StatPoint.class).equalTo("id", id).findFirst();
    }

    /**
     * Fetch a list of all the pojos in a realm that have a given role and summonerId.
     * @param realm The database to fetch from
     * @param role The role we are filtering by.
     * @param summonerId The summonerId we are filtering by.
     * @return The results.
     */
    private RealmResults<CardData> findSingleObjectUsingRole(Realm realm, int role, long summonerId) {
        return realm.where(CardData.class).equalTo("role", role).equalTo("summonerId", summonerId).findAll();
    }

    /**
     * Fetch a list of all the {@link MatchSummary} pojos that match the given criteria
     * @param realm
     * @param role
     * @param summonerId
     * @return
     */
    private RealmResults<MatchSummary> findMatchSummariesUsingRoleAndSummonerId(Realm realm, int role, long summonerId) {
        return realm.where(MatchSummary.class).equalTo("role", role).equalTo("summonerId", summonerId).findAll();
    }

    /**
     * Write a card data instance to the database.
     * @param cardData The data to save.
     */
    public void WriteSingleObjectToRealm(final CardData cardData) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CardData preExistingObject = findSingleObjectUsingRealm(realm, cardData.getId());
                if (preExistingObject == null) {
                    preExistingObject = realm.createObject(CardData.class, cardData.getId());
                }

                // Add other values.
                preExistingObject.setEnemyStats(cardData.getEnemyStats());
                preExistingObject.setFriendlyStats(cardData.getFriendlyStats());
                preExistingObject.setRole(cardData.getRole());
                preExistingObject.setTitle(cardData.getTitle());
                preExistingObject.setSummonerId(cardData.getSummonerId());
            }
        });
        realm.close();
    }

    /**
     * Write a card data instance to the database.
     * @param matchSummary The data to save.
     */
    public void SaveSingleMatchSummary(Realm realm, final MatchSummary matchSummary) {

        realm.executeTransaction(realm1 -> {
            MatchSummary preExistingObject = findSingleMatchSummary(realm1, matchSummary.getId());
            if (preExistingObject == null) {
                preExistingObject = realm1.createObject(MatchSummary.class, matchSummary.getId());
            }

            preExistingObject.setSummonerId(matchSummary.getSummonerId());
            preExistingObject.setValue(matchSummary.getValue());
            preExistingObject.setRole(matchSummary.getRole());
        });
    }




    /**
     * Store an entire data object (and array of CardObject in a specific role)
     * @param data
     */
    public void WriteDataObjectToRealm(final Data data, Realm realm) {
        List<CardData> pojos = data.getItems();
        Iterator<CardData> iterator = pojos.iterator();
        while (iterator.hasNext()) {
            WriteSingleObjectToRealm(iterator.next());
        }
    }

    /**
     * Find all the data for a role for that summoner
     * @param role
     * @param summonerId
     * @return
     */
    public Data FindDataForRole(int role, long summonerId, Realm realm) {
        RealmResults<CardData> results = findSingleObjectUsingRole(realm, role, summonerId);
        CardData[] rawPojos = new CardData[results.size()];
        List<CardData> items = Arrays.asList(results.toArray(rawPojos));
        Data data = new Data();
        data.setRole(role);
        data.setItems(items);
        return data;
    }

    /**
     * search for locally saved match history for a summoner in a specific role.
     * @param role The role we are limiting the  match history to.
     * @param summonerId The summonerId that we are fetching the match history for.
     * @param realm The realm for the db access.
     * @return The results as a {@link MatchHistoryData} object.
     */
    public MatchHistoryData FindMatchHistory(int role, long summonerId, Realm realm) {
//        RealmResults<MatchSummary> results = findMatchSummariesUsingRoleAndSummonerId(realm, role, summonerId);
//        MatchSummary[] matchSummaries = new MatchSummary[results.size()];
//        List<MatchIdWrapper> items = Arrays.asList(results.toArray(matchSummaries));
//        MatchHistoryData matchHistoryData = new MatchHistoryData();
//        matchHistoryData.setRole(role);
//        matchHistoryData.setItems(new ArrayList<MatchIdWrapper>(items));
      //  return matchHistoryData;
        return null;
    }

    public MatchSummary FindMatchSummary(Realm realm, long matchSummaryId) {
        return findSingleMatchSummary(realm, matchSummaryId);
    }

    /**
     * Save the card data for a match summary
     * @param realmInstance The db instance to use.
     * @param matchHistoryCardData The card data to save.
     */
    public void SaveMatchHistoryCardData(Realm realmInstance, MatchHistoryCardData matchHistoryCardData) {

        matchHistoryCardData.setDeaths(new CardData(3, 2, "Deaths"));
        realmInstance.executeTransaction(realm1 -> {
            MatchHistoryCardData preExistingData = findSingleMatchDetail(realm1, matchHistoryCardData.getMatchId());
            if (preExistingData == null) {
                preExistingData = realm1.createObject(MatchHistoryCardData.class, matchHistoryCardData.getMatchId());

                CardData csfirstTen = realm1.copyToRealmOrUpdate(matchHistoryCardData.getCsFirstTen());
                CardData csSecondTen = realm1.copyToRealmOrUpdate(matchHistoryCardData.getCsSecondTen());
                CardData csTotal = realm1.copyToRealmOrUpdate(matchHistoryCardData.getCsTotal());
                CardData deaths = realm1.copyToRealmOrUpdate(matchHistoryCardData.getDeaths());
                CardData kills = realm1.copyToRealmOrUpdate(matchHistoryCardData.getKills());

                preExistingData.setChampId(matchHistoryCardData.getChampId());
                preExistingData.setChampName("Champ 1");
                preExistingData.setCsFirstTen(csfirstTen);
                preExistingData.setCsSecondTen(csSecondTen);
                preExistingData.setCsTotal(csTotal);
                preExistingData.setDeaths(deaths);
                preExistingData.setKills(kills);
            }
        });
    }

    @Deprecated
    public void SaveMatchHistory(Realm realm, MatchHistoryData matchHistoryData) {
        List<MatchIdWrapper> matchSummaries = matchHistoryData.getItems();
        Iterator<MatchIdWrapper> matchSummaryIterator = matchSummaries.iterator();
        while (matchSummaryIterator.hasNext()) {
       //     SaveSingleMatchSummary(realm, matchSummaryIterator.next());
        }
    }

    /**
     * Load a stat point from a specified realm database.
     * @param realm The realm database we are using to load from.
     * @param statPoint The {@link StatPoint} instance that we are after.
     */
    public void SaveStatPoint(Realm realm, StatPoint statPoint) {
        realm.executeTransaction(realm1 -> {
            StatPoint preExistingObject = findSingleStatPoint(realm1, statPoint.getId());
            if (preExistingObject == null) {
                preExistingObject = realm1.createObject(StatPoint.class, statPoint.getId());
            }

            // Save the values
            preExistingObject.setStatId(statPoint.getStatId());
            preExistingObject.setxValue(statPoint.getxValue());
            preExistingObject.setyValue(statPoint.getyValue());
        });
    }

    public StatPoint LoadStatPoint(Realm realm, long id) {
        return findSingleStatPoint(realm, id);
    }

    private MatchHistoryCardData findSingleMatchDetail(Realm realm, long id) {
        return realm.where(MatchHistoryCardData.class).equalTo("matchId", id).findFirst();
    }

    private StatSummary findSingleStatData(Realm realm, long id) {
        return realm.where(StatSummary.class).equalTo("id", id).findFirst();
    }

    /**
     * Save a stat data object. This does two things
     * @param realm The realm db to use.
     * @param statSummary The {@link StatSummary} to save.
     */
    public void SaveStatSummary(Realm realm, StatSummary statSummary) {
        realm.executeTransaction(realm1 -> {
            StatSummary preExistingObject = findSingleStatData(realm1, statSummary.getId());
            if (preExistingObject == null) {
                preExistingObject = realm1.createObject(StatSummary.class, statSummary.getId());
            }

            preExistingObject.setGoalvalue(statSummary.getGoalvalue());
            preExistingObject.setHasGoal(statSummary.getHasGoal());
            preExistingObject.setImprovementValue(statSummary.getImprovementValue());
            preExistingObject.setStatName(statSummary.getStatName());
        });
    }

    public StatSummary LoadStatData(Realm realm, long id) {
        return findSingleStatData(realm, id);
    }

    public void SaveArrayOfStatPoints(Realm realm, ArrayList<StatPoint> statPoints) {
        for (StatPoint statPoint : statPoints) {
            // Save our stat point
            SaveStatPoint(realm, statPoint);
        }
    }

    /**
     * Load an arraylist of {@link StatPoint} objects for a stat.
     * @param realm The db to load with.
     * @param statId The statistic the points belong to.
     * @return The arraylist of statpoints.
     */
    public ArrayList<StatPoint> FindStatPoints(Realm realm, long statId) {
        RealmResults<StatPoint> results = realm.where(StatPoint.class).equalTo("statId", statId).findAll();
        StatPoint[] statPointsArray = new StatPoint[results.size()];
        List<StatPoint> items = Arrays.asList(results.toArray(statPointsArray));
        return new ArrayList<>(items);
    }

    /**
     * Save an arraylist of matchIds.
     * @param realm The realm db to use with saving
     * @param matchIds The matchIds to save
     */
    public void SaveMatchList(Realm realm, ArrayList<MatchIdWrapper> matchIds) {

        for (MatchIdWrapper matchId: matchIds) {
            saveMatchId(realm, matchId);
        }
    }

    private MatchIdWrapper findSingleMatchId(Realm realm, long matchId) {
        return realm.where(MatchIdWrapper.class).equalTo("matchId", matchId).findFirst();
    }

    private void saveMatchId(Realm realm, MatchIdWrapper matchIdWrapper) {
        realm.executeTransaction(realm1 -> {
            MatchIdWrapper preexisitingMatchId = findSingleMatchId(realm1, matchIdWrapper.getMatchId());
            if (preexisitingMatchId == null) {
                preexisitingMatchId = realm1.createObject(MatchIdWrapper.class, matchIdWrapper.getMatchId());
            }
            preexisitingMatchId.setRole(matchIdWrapper.getRole());
            preexisitingMatchId.setSummonerId(matchIdWrapper.getSummonerId());
        });
    }

    /**
     * Load all the ids for a summoner with a specific role.
     * @param realm
     * @param summonerId
     * @return
     */
    public ArrayList<MatchIdWrapper> LoadMatchList(Realm realm, long summonerId) {
        RealmResults<MatchIdWrapper> results = realm.where(MatchIdWrapper.class).equalTo("summonerId", summonerId).findAll();
        MatchIdWrapper[] statPointsArray = new MatchIdWrapper[results.size()];
        List<MatchIdWrapper> items = Arrays.asList(results.toArray(statPointsArray));
        return new ArrayList<>(items);
    }

    public ArrayList<Entry> LoadEntries(long summonerId, int statId, Realm realm) {
        RealmResults<Entry> results = realm.where(Entry.class)
                                .equalTo("summonerId", summonerId)
                                .equalTo("statId", statId)
                                .findAll();

        Entry[] entries =new Entry[results.size()];
        List<Entry> items = Arrays.asList(results.toArray(entries));
        return new ArrayList<>(items);
    }
}
