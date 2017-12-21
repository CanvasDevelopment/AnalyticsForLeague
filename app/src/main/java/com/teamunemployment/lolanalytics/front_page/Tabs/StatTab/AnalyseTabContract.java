package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab;

import com.teamunemployment.lolanalytics.data.model.Champ;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList;
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public interface AnalyseTabContract {

    interface View extends TabContract.View {
        void SetAdapter(AnalyseAdapter adapter);
        void SetPlaceHolderVisible();
        void SetPlaceHolderInvisible();
        void SetPlaceHolderString(String string);
        void SetChamp(Champ champ);
    }

    interface CardView {
        void setTitle(String title);
//        void SetGraph(float enemyStat, float heroStat);
//        void SetChange(double change);
//        void SetItemPosition(int position);
        void setEarlyGame(float hero, float enemy);
        void setMidGame(float hero, float enemy);
        void setLateGame(float hero, float enemy);
    }

    interface Presenter {
        void setStatList(StatList statList);
        int getFilterListSize();
        void setView(View view);
        void start();
        void setRole(int support);
        void setChamp(Champ champ);
        void setPlaceHolder(String noResults);
        void onCardBinding(AnalyseTabContract.CardView viewHolder, int position);
        void handleItemClick(int position);

    }

    interface Adapter {
        void SetPresenter(Presenter presenter);
    }
}
