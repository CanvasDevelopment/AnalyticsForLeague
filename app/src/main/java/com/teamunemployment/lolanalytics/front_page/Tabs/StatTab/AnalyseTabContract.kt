package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

interface AnalyseTabContract {

    interface View : TabContract.View {
        fun setAdapter(adapter: AnalyseAdapter)
        fun setPlaceHolderVisible()
        fun setPlaceHolderInvisible()
        fun setPlaceHolderString(message: String)
        fun setChamp(champ: Champ)
    }

    interface CardView {
        fun setTitle(title: String)
        //        void SetGraph(float enemyStat, float heroStat);
        //        void SetChange(double change);
        //        void SetItemPosition(int position);
        fun setEarlyGame(hero: Float, enemy: Float)

        fun setMidGame(hero: Float, enemy: Float)
        fun setLateGame(hero: Float, enemy: Float)
    }

    interface Presenter {
        fun getFilterListSize() : Int
        fun setStatList(statList: StatList)
        fun setView(view: View)
        fun start()
        fun setRole(role: Int)
        fun setChamp(champ: Champ)
        fun setSummonerId(summonerId : Long)
        fun setPlaceHolder(noResults: String)
        fun onCardBinding(viewHolder: AnalyseTabContract.CardView, position: Int)
        fun handleItemClick(position: Int)

    }

    interface Adapter {
        fun setPresenter(presenter: Presenter)
    }
}
