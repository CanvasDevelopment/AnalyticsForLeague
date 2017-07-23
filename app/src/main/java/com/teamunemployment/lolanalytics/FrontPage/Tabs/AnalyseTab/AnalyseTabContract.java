package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab;

import android.support.v7.widget.RecyclerView;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model.AnalyseCardViewHolder;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model.AnalyseData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;

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
        void SetTitle(String title);
        void SetGraph(float enemyStat, float heroStat);
        void SetChange(double change);
        void SetItemPosition(int position);
    }

    interface Presenter {
        void SetFilterRequestResponse(ArrayList<AnalyseData> analyseDatas);
        int GetFilterListSize();
        void SetView(View view);
        void Start();
        void SetRole(int support);
        void SetChamp(Champ champ);
        void SetPlaceHolder(String noResults);
        void OnCardBinding(AnalyseTabContract.CardView viewHolder, int position);
        void HandleItemClick(int position);

    }

    interface Adapter {
        void SetPresenter(Presenter presenter);
    }
}
