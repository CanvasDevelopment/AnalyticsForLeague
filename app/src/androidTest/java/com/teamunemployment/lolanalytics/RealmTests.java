package com.teamunemployment.lolanalytics;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import com.teamunemployment.lolanalytics.Base.RealmInterface;
import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void TestWeCanGetSingleObjectFromRealDb() {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmInterface realmInterface = new RealmInterface(realm);

        AdapterPojo adapterPojo = new AdapterPojo(12, 5, "test", 1, "TOP");
        realmInterface.WriteSingleObjectToRealm(adapterPojo);
        AdapterPojo result = realmInterface.GetSingleAdapterObect(1);
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getEnemyStats(), 12.0);
        Assert.assertEquals(result.getFriendlyStats(), 5.0);
        Assert.assertEquals(result.getTitle(), "test");
    }

    @Test
    public void TestWeCanSaveAndLoadListOfData() {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmInterface realmInterface = new RealmInterface(realm);

        ArrayList<AdapterPojo> adapterPojos = new ArrayList<>();
        AdapterPojo adapterPojo = new AdapterPojo(12, 5, "test", 1, "TOP");
        AdapterPojo adapterPojo1 = new AdapterPojo(12, 5, "test", 2, "TOP");
        AdapterPojo adapterPojo2 = new AdapterPojo(12, 5, "test", 3, "TOP");
        AdapterPojo adapterPojo3 = new AdapterPojo(12, 5, "test", 4, "TOP");
        AdapterPojo adapterPojo4 = new AdapterPojo(12, 5, "test", 5, "TOP");

        adapterPojos.add(adapterPojo);
        adapterPojos.add(adapterPojo1);
        adapterPojos.add(adapterPojo2);
        adapterPojos.add(adapterPojo3);
        adapterPojos.add(adapterPojo4);

        Data data = new Data();
        data.setItems(adapterPojos);

        realmInterface.WriteDataObjectToRealm(data);

        Data cached = realmInterface.FindDataForRole(realm, "TOP");

        Assert.assertEquals(cached.getItems().size(), 5);
        Assert.assertEquals(cached.getItems().get(0).getId(), 1);
        Assert.assertEquals(cached.getItems().get(1).getId(), 2);
        Assert.assertEquals(cached.getItems().get(2).getId(), 3);
        Assert.assertEquals(cached.getItems().get(3).getId(), 4);
        Assert.assertEquals(cached.getItems().get(4).getId(), 5);
    }

    @Test
    public void TestThatWeCanUpdateValue() {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmInterface realmInterface = new RealmInterface(realm);

        AdapterPojo adapterPojo = new AdapterPojo(12, 5, "test", 1, "TOP");
        realmInterface.WriteSingleObjectToRealm(adapterPojo);
        AdapterPojo result = realmInterface.GetSingleAdapterObect(1);
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getEnemyStats(), 12.0);
        Assert.assertEquals(result.getFriendlyStats(), 5.0);
        Assert.assertEquals(result.getTitle(), "test");

        adapterPojo.setFriendlyStats(8.3);
        realmInterface.WriteSingleObjectToRealm(adapterPojo);

        result = realmInterface.GetSingleAdapterObect(1);
        Assert.assertEquals(result.getId(), 1);
        Assert.assertEquals(result.getFriendlyStats(), 8.3);
    }

}
