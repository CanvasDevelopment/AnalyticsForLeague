package com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab.Model.WinRateResult;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author Josiah Kendall.
 */

public class CoachInteractor {

    private CoachPresenter presenter;
    private Context context;

    public CoachInteractor(Context context) {
        this.context = context;
    }

    public void setPresenter(CoachPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Load win rates for a user.
     * @param userId
     */
    public void LoadWinRates(long userId) {
        //load cached lane win rate
        // load cached game win rate
        // emit result
        // load network lane win rate
        // load network game win rate
        // emit result
//        Observable<WinRateResult> cachedWinRateResultObservable = Observable.create(e -> {
//            Realm realm = Realm.getDefaultInstance();
//            RealmExecutor realmExecutor = new RealmExecutor(context);
//            ArrayList<Entry> gameWinRate = realmExecutor.LoadEntries(userId, 0, realm);
//            ArrayList<Entry> laneWinRate = realmExecutor.LoadEntries(userId, 1, realm);
//
//            WinRateResult winRateResult = new WinRateResult();
//
//
//        });

        WinRateResult winRateResult = getDemoWinRate();
        presenter.setWinRates(winRateResult);

    }

    private WinRateResult getDemoWinRate() {

        ArrayList<com.github.mikephil.charting.data.Entry> laneWinRates = fetchMockData();
        ArrayList<com.github.mikephil.charting.data.Entry> gameWinRates = fetchMockData();
        WinRateResult winRateResult = new WinRateResult(laneWinRates, gameWinRates);
        return winRateResult;
    }

    // TODO remove
    private ArrayList<com.github.mikephil.charting.data.Entry> fetchMockData() {

        Random random = new Random();
        ArrayList<com.github.mikephil.charting.data.Entry> entries = new ArrayList<>();
        ArrayList<Integer> fiveMax = new ArrayList<>();
        int i =0;
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
            com.github.mikephil.charting.data.Entry entry = new com.github.mikephil.charting.data.Entry(i,sum);
            entries.add(entry);
        }
        return entries;
    }

    private Float calculateAverage(ArrayList<Integer> arrayList) {
        int total = 0;
        for (Integer x: arrayList) {
            total += x;
        }

        return ((float) total/(float)arrayList.size());
    }
    private ArrayList<com.github.mikephil.charting.data.Entry> getMockLaneWinRates() {

        ArrayList<com.github.mikephil.charting.data.Entry> entries = new ArrayList<>();
        int rand = 3;
        int base = 41;
        double increase = 2;
        Random random;
        float index = 0;
        while (index < 50) {
            random = new Random();
            float score = random.nextInt(rand);
            score += base;
            score += increase;
            increase += 0.5;
            com.github.mikephil.charting.data.Entry entry = new com.github.mikephil.charting.data.Entry(index, score);
            entries.add(entry);
            index += 1;
        }

        return entries;
    }

    public ArrayList<Integer> FetchCardIds(long userId) {
        return fetchDemoCardIds();
    }

    public ArrayList<Integer> fetchDemoCardIds() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        return integers;
    }

}
