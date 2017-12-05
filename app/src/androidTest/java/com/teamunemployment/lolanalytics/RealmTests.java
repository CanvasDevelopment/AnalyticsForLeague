package com.teamunemployment.lolanalytics;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import com.teamunemployment.lolanalytics.data.realm.RealmMigrator;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.data.Statics;
import com.teamunemployment.lolanalytics.data.model.Data;
import com.teamunemployment.lolanalytics.data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.data.model.MatchSummary;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatSummary;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatPoint;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model.CardData;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Josiah Kendall
 */
@RunWith(AndroidJUnit4.class)
public class RealmTests {

    private Context context;

    @Before
    public void setup() {
         context = new RenamingDelegatingContext(InstrumentationRegistry.getInstrumentation().getTargetContext(), "test_");
    }

    @Test
    public void TestWeCanGetSingleObjectFromRealDb() throws FileNotFoundException {
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = Realm.getDefaultInstance();
        CardData cardData = new CardData(12, 5, "test", 1, 0, -1);
        realmExecutor.WriteSingleObjectToRealm(cardData);
        CardData result = realmExecutor.GetCardDataByCardId(realm, 1);
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getEnemyStats(), 12.0);
        Assert.assertEquals(result.getFriendlyStats(), 5.0);
        Assert.assertEquals(result.getTitle(), "test");
        realm.close();
    }

    @Test
    public void TestWeCanSaveAndLoadListOfData() {
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm.init(context);
        ArrayList<CardData> cardDatas = new ArrayList<>();
        CardData cardData = new CardData(12, 5, "test", 1, Statics.TOP, -2);
        CardData cardData1 = new CardData(12, 5, "test", 2, Statics.TOP, -2);
        CardData cardData2 = new CardData(12, 5, "test", 3, Statics.TOP, -2);
        CardData cardData3 = new CardData(12, 5, "test", 4, Statics.TOP, -2);
        CardData cardData4 = new CardData(12, 5, "test", 5, Statics.TOP, -2);
        CardData cardData5 = new CardData(12, 5, "test", 6, Statics.TOP, -2);

        cardDatas.add(cardData);
        cardDatas.add(cardData1);
        cardDatas.add(cardData2);
        cardDatas.add(cardData3);
        cardDatas.add(cardData4);
        cardDatas.add(cardData5);

        Data data = new Data();
        data.setItems(cardDatas);
        Realm realm = Realm.getDefaultInstance();

        realmExecutor.WriteDataObjectToRealm(data, realm);
        Data cached = realmExecutor.FindDataForRole(Statics.TOP, -2, realm);

        Assert.assertEquals(cached.getItems().size(), 6);
        Assert.assertEquals(cached.getItems().get(0).getId(), 1);
        Assert.assertEquals(cached.getItems().get(1).getId(), 2);
        Assert.assertEquals(cached.getItems().get(2).getId(), 3);
        Assert.assertEquals(cached.getItems().get(3).getId(), 4);
        Assert.assertEquals(cached.getItems().get(4).getId(), 5);
    }

    @Test
    public void TestThatWeCanUpdateValue() {
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        CardData cardData = new CardData(12, 5, "test", 1, Statics.TOP, -1);
        realmExecutor.WriteSingleObjectToRealm(cardData);
        CardData result = realmExecutor.GetCardDataByCardId(realm, 1);
        // Need to re init real so we dont get errors
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getEnemyStats(), 12.0);
        Assert.assertEquals(result.getFriendlyStats(), 5.0);
        Assert.assertEquals(result.getTitle(), "test");

        cardData.setFriendlyStats(8.3);
        realmExecutor.WriteSingleObjectToRealm(cardData);

        result = realmExecutor.GetCardDataByCardId(realm, 1);
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getFriendlyStats(), 8.3);
        realm.close();
    }

    @Test
    public void TestThatWeCanMIgrateRealm() {

    }

    @Test
    public void TestThatWeCanSaveMatchListOfOne() {
//
//        Realm.init(context);
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .name("default1.realm")
//                .schemaVersion(2)
//                .migration(new RealmMigrator())
//                .build();
//
//
//        long SUMMONERID = -1;
//        int ROLE = Statics.TOP;
//        int preSaveMatchSize = 0;
//
//        RealmExecutor realmExecutor = new RealmExecutor(context);
//        Realm realm = RealmExecutor.GetRealmInstance(realmConfiguration);
//
//        MatchHistoryData matchHistoryDataReturned = realmExecutor.FindMatchHistory(ROLE, SUMMONERID, realm);
//        List<MatchSummary> items = matchHistoryDataReturned.getItems();
//        if (items != null) {
//            preSaveMatchSize = items.size();
//        }
//
//        // Create our match history value object
//        MatchHistoryData matchHistoryData = new MatchHistoryData();
//        matchHistoryData.setRole(Statics.TOP);
//        matchHistoryData.setSeason(7);
//
//        // Build the list of one.
//        MatchSummary matchSummary = new MatchSummary();
//        matchSummary.setRole(Statics.TOP);
//        matchSummary.setId(1234);
//        matchSummary.setValue(2.34);
//        matchSummary.setSummonerId(-1);
//        ArrayList<MatchSummary> matchSummaries = new ArrayList<>();
//        matchSummaries.add(matchSummary);
//
//        matchHistoryData.setItems(matchSummaries);
//
//        realmExecutor.SaveMatchHistory(realm, matchHistoryData);
//        matchHistoryDataReturned = realmExecutor.FindMatchHistory(ROLE, SUMMONERID, realm);
//
//        items = matchHistoryDataReturned.getItems();
//
//        Assert.assertTrue(items.size()  == preSaveMatchSize);
//
//        MatchSummary matchSummary1 = items.get(items.size()-1);
//
//        Assert.assertEquals(matchSummary1.getId(), matchSummary.getId());
//        Assert.assertEquals(matchSummary1.getRole(), matchSummary.getRole());
//        Assert.assertEquals(matchSummary1.getValue(), matchSummary.getValue());
//        realm.close();
    }

    @Test
    public void TestThatWeCanSaveMatchSummary() {
        Realm.init(context);

        long SUMMONERID = -1;
        int ROLE = Statics.TOP;
        int preSaveMatchSize = 0;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("default1.realm")
                .schemaVersion(2)
                .migration(new RealmMigrator())
                .build();

        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = RealmExecutor.GetRealmInstance(realmConfiguration);

        // Create our match history value object
        MatchHistoryData matchHistoryData = new MatchHistoryData();
        matchHistoryData.setRole(Statics.TOP);
        matchHistoryData.setSeason(7);

        // Build the list of one.
        MatchSummary matchSummary = new MatchSummary();
        matchSummary.setRole(Statics.TOP);
        matchSummary.setId(1234);
        matchSummary.setValue(2.34);
        matchSummary.setSummonerId(-1);

        realmExecutor.SaveSingleMatchSummary(realm,matchSummary);
        MatchSummary matchSummary1 = realmExecutor.FindMatchSummary(realm, matchSummary.getId());
        Assert.assertTrue(matchSummary.getValue() == matchSummary1.getValue());
        Assert.assertTrue(matchSummary.getRole() == matchSummary1.getRole());
        realm.close();
    }

    @Test
    public void TestThatWeCanUpdateMatchSummary() {

    }

    @Test
    public void SaveStatPoint_SavesStatPointAndLoadStatPoint_LoadsStatPoint() {
        Realm.init(context);
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = Realm.getDefaultInstance();
        StatPoint statPoint = new StatPoint();
        statPoint.setId(1);
        statPoint.setyValue(12);
        statPoint.setxValue(3.1);
        statPoint.setStatId(1);

        realmExecutor.SaveStatPoint(realm, statPoint);
        StatPoint result = realmExecutor.LoadStatPoint(realm, 1);
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getxValue(), 3.1);
        Assert.assertEquals(result.getyValue(), 12.0);
        realm.close();
    }

    @Test
    public void SaveStatSummary_SavesStatSummaryObjectAndLoadStatSummary_LoadsStatSummary() {
        StatSummary statSummary = new StatSummary();
        statSummary.setHasGoal(true);
        statSummary.setStatName("Stat 1");
        statSummary.setGoalvalue(23.1);
        statSummary.setId(234);

       // statSummary.setStatPointDetails(statPoints);

        Realm.init(context);
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = Realm.getDefaultInstance();
        realmExecutor.SaveStatSummary(realm, statSummary);

        StatSummary statSummary1 = realmExecutor.LoadStatData(realm, statSummary.getId());
        Assert.assertEquals(statSummary1.getGoalvalue(), statSummary.getGoalvalue());
        Assert.assertEquals(statSummary1.getImprovementValue(), statSummary.getImprovementValue());
    }

    @Test
    public void SaveArrayOfStatPoints_SavesArrayOfStatPoints() {
        StatSummary statSummary = new StatSummary();
        statSummary.setHasGoal(true);
        statSummary.setStatName("Stat 1");
        statSummary.setGoalvalue(23.1);
        statSummary.setId(234);

        ArrayList<StatPoint> statPoints = new ArrayList<>();
        StatPoint statPoint = new StatPoint();
        statPoint.setStatId(234);
        statPoint.setyValue(1);
        statPoint.setxValue(1.2);

        StatPoint statPoint2 = new StatPoint();
        statPoint.setId(1);
        statPoint2.setStatId(234);
        statPoint2.setyValue(1);
        statPoint2.setxValue(1.2);

        statPoints.add(statPoint);
        statPoints.add(statPoint2);

        Realm.init(context);
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = Realm.getDefaultInstance();
        Realm.init(context);
        realmExecutor.SaveArrayOfStatPoints(realm, statPoints);

        ArrayList<StatPoint> statPoints1 = realmExecutor.FindStatPoints(realm, 234);
        Assert.assertEquals(statPoints.size(), statPoints1.size());
    }

    @Test
    public void SaveArrayOfMatchIds_SavesArrayOfMAtchIds() {
        MatchIdWrapper matchIdWrapper = new MatchIdWrapper();
        matchIdWrapper.setMatchId(12345);
        matchIdWrapper.setSummonerId(-2);
        matchIdWrapper.setRole(1);

        MatchIdWrapper matchIdWrapper2 = new MatchIdWrapper();
        matchIdWrapper2.setMatchId(12346);
        matchIdWrapper2.setSummonerId(-2);
        matchIdWrapper2.setRole(1);

        MatchIdWrapper matchIdWrapper3 = new MatchIdWrapper();
        matchIdWrapper3.setMatchId(12347);
        matchIdWrapper3.setSummonerId(-2);
        matchIdWrapper3.setRole(1);

        ArrayList<MatchIdWrapper> matchIdWrappers = new ArrayList<>();
        matchIdWrappers.add(matchIdWrapper);
        matchIdWrappers.add(matchIdWrapper2);
        matchIdWrappers.add(matchIdWrapper3);

        Realm.init(context);
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = Realm.getDefaultInstance();
        realmExecutor.SaveMatchList(realm, matchIdWrappers);
        ArrayList<MatchIdWrapper> matchIdWrappers1 = realmExecutor.LoadMatchList(realm, -2);
        Assert.assertEquals(matchIdWrappers1.size(), 3);
    }



}
