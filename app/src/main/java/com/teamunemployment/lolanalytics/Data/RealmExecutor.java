package com.teamunemployment.lolanalytics.Data;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.Realm.RealmMigrator;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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

    public static Realm GetRealmInstance() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("default1.realm")
                .schemaVersion(2)
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
        CardData cardData = findSingleObjectUsingRealm(realm, id);
        return cardData;
    }

    // Private version for fetching a single object by id.
    private CardData findSingleObjectUsingRealm(Realm realm, int id) {
        return realm.where(CardData.class).equalTo("id", id).findFirst();
    }

    // Private version for fetching a single object by id.
    private MatchSummary findSingleMatchSummary(Realm realm, long id) {
        return realm.where(MatchSummary.class).equalTo("id", id).findFirst();
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

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MatchSummary preExistingObject = findSingleMatchSummary(realm, matchSummary.getId());
                if (preExistingObject == null) {
                    preExistingObject = realm.createObject(MatchSummary.class, matchSummary.getId());
                }

                preExistingObject.setSummonerId(matchSummary.getSummonerId());
                preExistingObject.setValue(matchSummary.getValue());
                preExistingObject.setRole(matchSummary.getRole());
            }
        });
    }




    /**
     * Store an entire data object (and array of CardObject in a specific role)
     * @param data
     */
    public void WriteDataObjectToRealm(final Data data) {
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
        RealmResults<MatchSummary> results = findMatchSummariesUsingRoleAndSummonerId(realm, role, summonerId);
        MatchSummary[] matchSummaries = new MatchSummary[results.size()];
        List<MatchSummary> items = Arrays.asList(results.toArray(matchSummaries));
        MatchHistoryData matchHistoryData = new MatchHistoryData();
        matchHistoryData.setRole(role);
        matchHistoryData.setItems(new ArrayList<>(items));
        return matchHistoryData;
    }

    public MatchSummary FindMatchSummary(Realm realm, long matchSummaryId) {
        return findSingleMatchSummary(realm, matchSummaryId);
    }

    public void SaveMatchHistory(Realm realm, MatchHistoryData matchHistoryData) {
        List<MatchSummary> matchSummaries = matchHistoryData.getItems();
        Iterator<MatchSummary> matchSummaryIterator = matchSummaries.iterator();
        while (matchSummaryIterator.hasNext()) {
            SaveSingleMatchSummary(realm, matchSummaryIterator.next());
        }
    }
}
