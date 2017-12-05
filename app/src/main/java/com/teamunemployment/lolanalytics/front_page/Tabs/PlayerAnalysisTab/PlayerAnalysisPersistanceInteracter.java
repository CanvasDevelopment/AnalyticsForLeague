package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab;

import android.content.Context;

import com.github.mikephil.charting.data.Entry;
import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatDefinitionWrapper;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatSummary;
import com.teamunemployment.lolanalytics.Utils.Network;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * @author Josiah Kendall
 *
 * The persisitance interactor to load value from the local and remote storage for the analysis tab.
 */

public class PlayerAnalysisPersistanceInteracter {

    private RESTApiExecutor restApiExecutor;
    private RealmExecutor realmExecutor;
    private Context context;

    @Inject
    public PlayerAnalysisPersistanceInteracter(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor, Context context) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
        this.context = context;
    }

    private void loadStatAnalysisCard(int role,
                                      long summonerId,
                                      int statId,
                                      Observer<StatCollection> observer,
                                      Function<StatCollection, StatCollection> mapFunction) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        ArrayList<Entry> statResults = fetchMockData();
        ArrayList<Entry> enemyStatResults = fetchMockEnemyData();
        StatCollection statCollection = new StatCollection();
        StatSummary statSummary = new StatSummary();
        statSummary.setImprovementValue(getImprovementValue(statId));
        statSummary.setStatName(getStatName(statId));
        statSummary.setSubName(getSubName(statId));
        statCollection.setStatSummary(statSummary);
        statCollection.setCollection(statResults,enemyStatResults);
        observer.onNext(statCollection);

        /*
        if (Network.isConnectingToInternet(context)) {
            Observable<StatCollection> statCollectionObservable = restApiExecutor.GetAnalysisStatCollection(role, summonerId, statId);
            statCollectionObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .map(mapFunction)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
*/
    }

    private double getImprovementValue(int statId) {
        switch (statId) {
            case 0 :
                return 65.3;
            case 1:
                return 61.6;
            case 2:
                return 187.4;
            case 3:
                return 2.3;
            case 4:
                return 3.1;
            case 5:
                return 7.1;
            case 6:
                return 2.8;
            case 7:
                return 2.5;
            case 8:
                return 6.9;
            case 9:
                return 15213;
            case 10:
                return 21547;
            case 11:
                return 73114;
        }

        return 1;
    }

    private void loadStatTypes(int role,
                               long summonerId,
                               Observer<StatDefinitionWrapper> observerCallback,
                               Function<StatDefinitionWrapper, StatDefinitionWrapper> mapFunction) {

        if (Network.isConnectingToInternet(context)) {
            Observable<StatDefinitionWrapper> statDefinitionWrapperObservable = restApiExecutor.GetStatDefinitions(summonerId, role);
            statDefinitionWrapperObservable.
                    subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .map(mapFunction)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observerCallback);
        }

    }

    /**
     * Load the Analysis statistic cards for a summoner
     * @param role The role to filter for e.g TOP
     * @param summonerId The summoner to filter to.
     * @param callback The callback for the observables onSubscibe.
     */
    public void LoadStatAnalysisCardObjects(int role,
                                            long summonerId,
                                            int statId,
                                            Observer<StatCollection> callback) {
        Function<StatCollection, StatCollection> mappingFunction = collection -> {
            // TODO implement realm stuff here.
            return collection;
        };

        loadStatAnalysisCard(role, summonerId, statId, callback, mappingFunction);
    }

    /**
     * Load the list of stats that we want to display.
     * @param role The Role we want the stats for.
     * @param summonerId The summoner we want the stats for.
     * @param callback The callback for success.
     */
    public void LoadStatTypes(int role, long summonerId, Observer<StatDefinitionWrapper> callback) {
        Function<StatDefinitionWrapper, StatDefinitionWrapper> cacheMapFunction = collection -> {
            // TODO implement realm stuff here.
            return collection;
        };

        loadStatTypes(role, summonerId, callback, cacheMapFunction);
    }

    /**
     * Load The stats that we need for the cards. This method is mainly used for testing the private version.
     * @param role The role to filter by E.g TOP.
     * @param summonerId The Summoner to filter by.
     * @param callback The callback for the observables subscribe.
     * @param mapFunction Map the value before saving it. Must return a statCollection. Used for caching.
     */
    public void LoadStatAnalysisCardObjects(int role, long summonerId, int statId, Observer<StatCollection> callback, Function<StatCollection, StatCollection> mapFunction) {
        loadStatAnalysisCard(role, summonerId, statId, callback, mapFunction);
    }

    /**
     * Load all the stat points for a single stat.
     * @param statId The stat we are interested in.
     * @param summonerId The summoner we are filtering by. Not really sure this is neccessary.
     * @param observer The callback.
     */
    public void LoadStatHistory(long statId, long summonerId, Observer<StatSummary> observer) {

    }


    // TODO remove
    private ArrayList<Entry> fetchMockData() {

        Random random = new Random();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Integer> fiveMax = new ArrayList<>();
        int i = 0;
        while ( i <= 100) {
            i += 1;
            int x = random.nextInt(25);
            if (fiveMax.size() < 5) {
                fiveMax.add(0, x);
            } else {
                fiveMax.remove(4);
                fiveMax.add(0, x);
            }
            float sum = calculateAverage(fiveMax);
            sum += 40;
            Entry entry = new Entry(i,sum);
            entries.add(entry);
        }
        return entries;
    }

    public ArrayList<Entry> fetchMockEnemyData() {

            Random random = new Random();
            ArrayList<Entry> entries = new ArrayList<>();
            ArrayList<Integer> fiveMax = new ArrayList<>();
            int i =0;
            while ( i <= 100) {
                i += 1;
                int x = random.nextInt(27);
                if (fiveMax.size() < 5) {
                    fiveMax.add(0, x);
                } else {
                    fiveMax.remove(4);
                    fiveMax.add(0, x);
                }
                float sum = calculateAverage(fiveMax);
                sum += 33;
                Entry entry = new Entry(i,sum);
                entries.add(entry);
            }
            return entries;
    }
    private String getStatName(int id) {
        switch (id) {
            case 0:
                return "Creep Score";
            case 1:
                return "Creep Score";
            case 2:
                return "Creep Score";
            case 3:
                return "Kills";
            case 4:
                return "Kills";
            case 5:
                return "Kills";
            case 6:
                return "Deaths";
            case 7:
                return "Deaths";
            case 8:
                return "Deaths";
            case 9:
                return "Damage Taken";
            case 10:
                return "Damage Taken";
            case 11:
                return "Damage Taken";
            case 12 :
                return "Damage Dealt";
            case 13:
                return "Damage Dealt";
            case 14:
                return "Damage Dealt";
        }
        return null;
    }

    private String getSubName(int id) {
        switch(id) {
            case 0 :
                return "Early Game";
            case 1 :
                return "Mid Game";
            case 2 :
                return "Late Game";
            case 3 :
                return "Early Game";
            case 4 :
                return "Mid Game";
            case 5 :
                return "Late Game";
            case 6 :
                return "Early Game";
            case 7 :
                return "Mid Game";
            case 8 :
                return "Late Game";
            case 9 :
                return "Early Game";
            case 10 :
                return "Mid Game";
            case 11 :
                return "Late Game";
        }
        return "Early Game";
    }


    private Float calculateAverage(ArrayList<Integer> arrayList) {
        int total = 0;
        for (Integer x: arrayList) {
            total += x;
        }

        return ((float) total/(float)arrayList.size());
    }
}
