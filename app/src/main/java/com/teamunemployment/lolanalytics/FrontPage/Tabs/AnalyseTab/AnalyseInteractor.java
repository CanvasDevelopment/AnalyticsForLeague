package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model.AnalyseData;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class AnalyseInteractor {

    public void RequestFilterList(int role, int champId, AnalyseTabContract.Presenter presenter) {
        ArrayList<AnalyseData> datas = new ArrayList<>();
        AnalyseData analyseData = new AnalyseData();
        presenter.SetFilterRequestResponse(removeMeAfterMocking());
    }

    public void RequestFilterList(int role, AnalysePresenter presenter) {
        presenter.SetFilterRequestResponse(removeMeAfterMocking());
    }

    /**
     * TODO remove me
     */
    private ArrayList<AnalyseData> removeMeAfterMocking() {
        AnalyseData analyseData1 = new AnalyseData();
        AnalyseData analyseData2 = new AnalyseData();
        AnalyseData analyseData3 = new AnalyseData();
        AnalyseData analyseData4 = new AnalyseData();
        AnalyseData analyseData5 = new AnalyseData();
        AnalyseData analyseData6 = new AnalyseData();

        analyseData1.setRecentChange(-3.1);
        analyseData2.setRecentChange(-2.3);
        analyseData3.setRecentChange(3.3);

        analyseData1.setTitle("Early Game");
        analyseData2.setTitle("Mid Game");
        analyseData3.setTitle("Late Game");

        analyseData1.setHeroPercentTotal(45);
        analyseData2.setHeroPercentTotal(44);
        analyseData3.setHeroPercentTotal(47);

        analyseData1.setEnemyPercentTotal(55);
        analyseData2.setEnemyPercentTotal(56);
        analyseData3.setEnemyPercentTotal(53);

        ArrayList<AnalyseData> analyseDatalist = new ArrayList<AnalyseData>();
        analyseDatalist.add(analyseData1);
        analyseDatalist.add(analyseData2);
        analyseDatalist.add(analyseData3);
        return analyseDatalist;

    }
}
