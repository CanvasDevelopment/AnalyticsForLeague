package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_STAT
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_STAT
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.ViewProducer
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.FullStatCard
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatSummary
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.StatCardView

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalysePresenter(private val analyseInteractor: AnalyseInteractor,
                       private val analyseAdapter: AnalyseAdapter,
                       private val roleUtils: RoleUtils) {
    private lateinit var view: AnalyseTabContract.View

    private var role = -1
    private var champ: Champ? = null
    private val filterList = ArrayList<AnalysisData>()
    private lateinit var statList : ArrayList<StatSummary>

    fun setStatList(statList : ArrayList<StatSummary>) {
        this.statList = statList
        // If we have data
        if (statList.size > 0) {
            view.setAdapter(analyseAdapter)
            view.setPlaceHolderInvisible()
        } else {
            // This means we have no value. Show an appropriate message to the user based on their settings.
            if (champ != null) {
                val message = String.format(
                        "No games found playing %s with %s",
                        roleUtils.getRoleName(role),
                        champ!!.champName)
                setPlaceHolder(message)
            } else {
                val message = "No games found playing ${roleUtils.getRoleName(role)}"
                setPlaceHolder(message)

            }


        }
    }

    fun handleListResult(result : Result<StatList>) {

    }

    fun handleCardResult(result: Result<FullStatCard>, viewHolder: AnalyseTabContract.CardView) {

    }

    fun getFilterListSize(): Int {
        return filterList.size
    }

    fun setView(view: AnalyseTabContract.View) {
        this.view = view
    }

    fun start() {

        if (role == -1) {
            throw IllegalStateException("Must set role before calling start")
        }

        analyseAdapter.setPresenter(this)

        // If we have a champ, include it. If we dont, don include it.
        if (champ != null) {
            analyseInteractor.loadStatList(role, champ!!.champId, this, "OCE")
        } else {
            analyseInteractor.loadStatList(role, this, "OCE", -1)
        }
    }

    fun setRole(role: Int) {
        this.role = role
    }

    fun setChamp(champ: Champ) {
        this.champ = champ
    }

    fun setPlaceHolder(noResults: String) {
        view.setPlaceHolderVisible()
        view.setPlaceHolderString(noResults)
    }

    fun onCardBinding(viewHolder: StatCardView, position: Int) {
        // load the data. on the result, fetch from
        val statUrl = statList[position]
        viewHolder.setTitle(statUrl.title)
        viewHolder.setCardPosition(position)
        analyseInteractor.loadIndividualStat("", -1, viewHolder,this)
        // If card view individual instance - load individual with appropriate calllback
        // else if cardview set, load set of details
    }

    fun handleItemClick(position: Int) {
        val filter = filterList[position]
        // launch new activtiy with the value.
    }

    /**
     * Handles the creation of the create view holder.
     * Creates a [StatCardView], which can be one of
     * [HALF_AD] -      An advertisement that covers half the recycler
     * [FULL_AD] -      An advertisement that covers the whole recycler.
     * [HALF_STAT] -    A stat card with just one chart/stat.
     * [FULL_STAT] -    A stat card with three charts - an early, mid and late game
     */
    fun createViewType(viewProducer: ViewProducer): StatCardView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
