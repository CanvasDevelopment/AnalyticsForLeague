package com.teamunemployment.lolanalytics.front_page.Tabs.AnalyseTab;

import com.teamunemployment.lolanalytics.front_page.Tabs.AnalyseTab.Model.AnalysisData;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class AnalyseInteractor {

    public void RequestFilterList(int role, int champId, AnalyseTabContract.Presenter presenter) {
        ArrayList<AnalysisData> datas = new ArrayList<>();
        AnalysisData analysisData = new AnalysisData();
        presenter.SetFilterRequestResponse(removeMeAfterMocking());
    }

    public void RequestFilterList(int role, AnalysePresenter presenter) {

        // send request to
        presenter.SetFilterRequestResponse(removeMeAfterMocking());
    }

    /**
     * TODO remove me
     */
    private ArrayList<AnalysisData> removeMeAfterMocking() {
        AnalysisData analysisData1 = new AnalysisData();
        AnalysisData analysisData2 = new AnalysisData();
        AnalysisData analysisData3 = new AnalysisData();
        AnalysisData analysisData4 = new AnalysisData();
        AnalysisData analysisData5 = new AnalysisData();
        AnalysisData analysisData6 = new AnalysisData();

        analysisData1.setRecentChange(-3.1);
        analysisData2.setRecentChange(-2.3);
        analysisData3.setRecentChange(3.3);

        analysisData1.setTitle("Early Game");
        analysisData2.setTitle("Mid Game");
        analysisData3.setTitle("Late Game");

        analysisData1.setHeroPercentTotal(45);
        analysisData2.setHeroPercentTotal(44);
        analysisData3.setHeroPercentTotal(47);

        analysisData1.setEnemyPercentTotal(55);
        analysisData2.setEnemyPercentTotal(56);
        analysisData3.setEnemyPercentTotal(53);

        ArrayList<AnalysisData> analysisDatalist = new ArrayList<AnalysisData>();
        analysisDatalist.add(analysisData1);
        analysisDatalist.add(analysisData2);
        analysisDatalist.add(analysisData3);
        return analysisDatalist;

    }
}
