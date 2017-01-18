package com.teamunemployment.lolanalytics.Base;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Josiah Kendall
 *
 * This is our access to the Realm NoSQL database that is used instead of sqlite. For more information
 * on real see https://realm.io/
 */

public class RealmInterface {
    private Realm realmUI;

    public RealmInterface(Realm realmUI) {
        this.realmUI = realmUI;
    }

    /**
     * Fetch a single object based on id.
     * @param id The id of the adapter pojo that we want
     * @return The
     */
    public AdapterPojo GetSingleAdapterObect(int id) {
        return findSingleObjectUsingReal(realmUI, id);
    }

    private AdapterPojo findSingleObjectUsingReal(Realm realm, int id) {
        return realm.where(AdapterPojo.class).equalTo("id", id).findFirst();
    }

    private RealmResults<AdapterPojo> findSingleObjectUsingRole(Realm realm, String role) {
        return realm.where(AdapterPojo.class).equalTo("role", role).findAll();
    }

    public void WriteSingleObjectToRealm(final AdapterPojo adapterPojo) {
        realmUI.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AdapterPojo preExistingObject = findSingleObjectUsingReal(realm, adapterPojo.getId());
                if (preExistingObject == null) {
                    preExistingObject = realm.createObject(AdapterPojo.class, adapterPojo.getId());
                }
                preExistingObject.setEnemyStats(adapterPojo.getEnemyStats());
                preExistingObject.setFriendlyStats(adapterPojo.getFriendlyStats());
                preExistingObject.setRole(adapterPojo.getRole());
                preExistingObject.setTitle(adapterPojo.getTitle());
            }
        });
    }

    public void WriteDataObjectToRealm(final Data data) {
        List<AdapterPojo> pojos = data.getItems();
        Iterator<AdapterPojo> iterator = pojos.iterator();
        while (iterator.hasNext()) {
            WriteSingleObjectToRealm(iterator.next());
        }
    }

    public Data FindDataForRole(Realm realm, String role) {
        RealmResults<AdapterPojo> results = findSingleObjectUsingRole(realm, role);
        AdapterPojo[] rawPojos = new AdapterPojo[results.size()];
        List<AdapterPojo> items = Arrays.asList(results.toArray(rawPojos));
        Data data = new Data();
        data.setRole(role);
        data.setItems(items);
        return data;
    }

}
