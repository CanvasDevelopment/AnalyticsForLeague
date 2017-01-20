package com.teamunemployment.lolanalytics;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import com.teamunemployment.lolanalytics.Base.RealmExecutor;
import com.teamunemployment.lolanalytics.Base.Statics;
import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Jungle.Model.CardData;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import io.realm.Realm;

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
        Realm realm = RealmExecutor.GetRealmInstance();
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

        ArrayList<CardData> cardDatas = new ArrayList<>();
        CardData cardData = new CardData(12, 5, "test", 1, Statics.TOP, -1);
        CardData cardData1 = new CardData(12, 5, "test", 2, Statics.TOP, -1);
        CardData cardData2 = new CardData(12, 5, "test", 3, Statics.TOP, -1);
        CardData cardData3 = new CardData(12, 5, "test", 4, Statics.TOP, -1);
        CardData cardData4 = new CardData(12, 5, "test", 5, Statics.TOP, -1);
        CardData cardData5 = new CardData(12, 5, "test", 6, Statics.TOP, 1);

        cardDatas.add(cardData);
        cardDatas.add(cardData1);
        cardDatas.add(cardData2);
        cardDatas.add(cardData3);
        cardDatas.add(cardData4);
        cardDatas.add(cardData5);

        Data data = new Data();
        data.setItems(cardDatas);

        realmExecutor.WriteDataObjectToRealm(data);
        Realm realm = Realm.getDefaultInstance();
        Data cached = realmExecutor.FindDataForRole(Statics.TOP, -1, realm);

        Assert.assertEquals(cached.getItems().size(), 5);
        Assert.assertEquals(cached.getItems().get(0).getId(), 1);
        Assert.assertEquals(cached.getItems().get(1).getId(), 2);
        Assert.assertEquals(cached.getItems().get(2).getId(), 3);
        Assert.assertEquals(cached.getItems().get(3).getId(), 4);
        Assert.assertEquals(cached.getItems().get(4).getId(), 5);
    }

    @Test
    public void TestThatWeCanUpdateValue() {
        RealmExecutor realmExecutor = new RealmExecutor(context);
        Realm realm = Realm.getDefaultInstance();
        CardData cardData = new CardData(12, 5, "test", 1, Statics.TOP, -1);
        realmExecutor.WriteSingleObjectToRealm(cardData);
        CardData result = realmExecutor.GetCardDataByCardId(realm, 1);
        // Need to re init real so we dont get errors
        Realm.init(context);
        Realm.getDefaultInstance();
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

}
