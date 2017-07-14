package com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab;

import com.github.mikephil.charting.data.Entry;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;
import com.teamunemployment.lolanalytics.PresenterContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */

public interface CoachContract {

    interface Presenter extends PresenterContract{
        void startLoadInfo(long userId);
        void setView(CoachContract.View view);
    }

    interface View extends TabContract.View {
        void setWinRateGraph(ArrayList<Entry> gameWinRate, ArrayList<Entry> laneWinRate);
        void setRecylcerViewAdapter(CoachHorizontalScrollerAdapter adapter);
        void setGoals(Object notReallySureWhatISGoingToGoHEre);
        void setIssues(Object notSureWhatHappensHere);

    }
}
