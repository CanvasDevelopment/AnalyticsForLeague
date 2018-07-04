package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalysePresenter(private val analyseInteractor: AnalyseInteractor,
                       private val analyseAdapter: AnalyseAdapter,
                       private val roleUtils: RoleUtils) : AnalyseTabContract.Presenter {

    private lateinit var view: AnalyseTabContract.View

    private val DEFAULT_GAMES = 20
    private var role = -1
    private var champ: Champ? = null
    private val filterList = ArrayList<AnalysisData>()
    private var summonerId = -1L
    private lateinit var statList : StatList

    override fun setStatList(statList : StatList) {
        this.statList = statList
        // If we have data
        if (statList.totalNumberOfGames > 0) {
            view.setAdapter(analyseAdapter)
            view.setPlaceHolderInvisible()
        } else {
            // This means we have no value. Show an appropriate message to the user based on their settings.
            val message = String.format(
                    "No games found playing %s with %s",
                    roleUtils.GetRoleName(role),
                    champ!!.champName)

            setPlaceHolder(message)
        }
    }

    fun handleListResult(result : Result<StatList>) {

    }

    fun handleCardResult(result: Result<ArrayList<HeadToHeadStat>>, viewHolder: AnalyseTabContract.CardView) {

    }

    override fun getFilterListSize(): Int {
        return filterList.size
    }

    override fun setView(view: AnalyseTabContract.View) {
        this.view = view
    }

    override fun start() {

        if (role == -1) {
            throw IllegalStateException("Must set role before calling start")
        }

        analyseAdapter.setPresenter(this)
        // If we have a champ, include it. If we dont, don include it.
        if (champ != null) {
            analyseInteractor.loadStatList(role, summonerId, champ!!.champId, DEFAULT_GAMES, this)
        } else {
            analyseInteractor.loadStatList(role, summonerId, DEFAULT_GAMES, this)
        }
    }

    override fun setRole(role: Int) {
        this.role = role
    }

    override fun setChamp(champ: Champ) {
        this.champ = champ
    }

    override fun setSummonerId(summonerId: Long) {
        this.summonerId = summonerId
    }

    override fun setPlaceHolder(noResults: String) {
        view.setPlaceHolderVisible()
        view.setPlaceHolderString(noResults)
    }

    override fun onCardBinding(viewHolder: AnalyseTabContract.CardView, position: Int) {
        // load the data. on the result, fetch from
        val statUrl = statList.stats[position]
        viewHolder.setTitle(statUrl.title)
        analyseInteractor.loadIndividualStat("", viewHolder,this)
    }

    override fun handleItemClick(position: Int) {
        val filter = filterList[position]
        // launch new activtiy with the value.
    }
}
