package com.teamunemployment.lolanalytics.Base;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * @author Josiah Kendall
 *
 * This is our access to the Realm NoSQL database that is used instead of sqlite. For more information
 * on realm see https://realm.io/. Realm was used as we are only using the db to cache the objects we
 * get from the server. This is much easier to do with Realm than sql.
 */
public class RealmInterface {
    private Context context;
    private Realm currentRealm;
    public RealmInterface(Context context) {
        this.context = context;
    }

    /**
     * Fetch a single object based on id.
     * @param id The id of the adapter pojo that we want
     * @return The
     */
    public AdapterPojo GetSingleAdapterObect(int id) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        currentRealm = realm;
        AdapterPojo adapterPojo = findSingleObjectUsingReal(realm, id);

        return adapterPojo;
    }

    // Private version for
    private AdapterPojo findSingleObjectUsingReal(Realm realm, int id) {
        return realm.where(AdapterPojo.class).equalTo("id", id).findFirst();
    }

    /**
     * Not sure if this method will be needed now that we filter by summonerId.
     * Fetch all the objects in a realm that match a specific role.
     * @param realm The database we are fetching from.
     * @param role The role to sort by.
     * @return The results.
     */
    @Deprecated
    private RealmResults<AdapterPojo> findSingleObjectUsingRole(Realm realm, String role) {
        return realm.where(AdapterPojo.class).equalTo("role", role).findAll();
    }

    /**
     * Fetch a list of all the pojos in a realm that have a given role and summonerId.
     * @param realm The database to fetch from
     * @param role The role we are filtering by.
     * @param summonerId The summonerId we are filtering by.
     * @return The results.
     */
    private RealmResults<AdapterPojo> findSingleObjectUsingRole(Realm realm, int role, long summonerId) {
        return realm.where(AdapterPojo.class).equalTo("role", role).equalTo("summonerId", summonerId).findAll();
    }

    /**
     * Write an adapter pojo to the database.
     * @param adapterPojo
     */
    public void WriteSingleObjectToRealm(final AdapterPojo adapterPojo) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AdapterPojo preExistingObject = findSingleObjectUsingReal(realm, adapterPojo.getId());
                if (preExistingObject == null) {
                    preExistingObject = realm.createObject(AdapterPojo.class, adapterPojo.getId());
                }

                // Add other values.
                preExistingObject.setEnemyStats(adapterPojo.getEnemyStats());
                preExistingObject.setFriendlyStats(adapterPojo.getFriendlyStats());
                preExistingObject.setRole(adapterPojo.getRole());
                preExistingObject.setTitle(adapterPojo.getTitle());
                preExistingObject.setSummonerId(adapterPojo.getSummonerId());
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

    public Data FindDataForRole(int role, long summonerId) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<AdapterPojo> results = findSingleObjectUsingRole(realm, role, summonerId);
        AdapterPojo[] rawPojos = new AdapterPojo[results.size()];
        List<AdapterPojo> items = Arrays.asList(results.toArray(rawPojos));
        Data data = new Data();
        data.setRole(role);
        data.setItems(items);
        return data;
    }

    public void Close() {
        currentRealm.close();
    }

}
