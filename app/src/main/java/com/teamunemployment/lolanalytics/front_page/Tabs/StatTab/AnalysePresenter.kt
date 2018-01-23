package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatCard

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalysePresenter(private val analyseInteractor: AnalyseInteractor, private val analyseAdapter: AnalyseAdapter, private val roleUtils: RoleUtils) : AnalyseTabContract.Presenter {
    private lateinit var view: AnalyseTabContract.View

    private var role = -1
    private var champ: Champ? = null
    private var filterList = ArrayList<AnalysisData>()
    private lateinit var statList : StatList

    override fun setStatList(statList : StatList) {

        this.statList = statList
        // If we have data
        if (statList.totalNumberOfGames > 0) {
            view.SetAdapter(analyseAdapter)
            view.SetPlaceHolderInvisible()
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

    fun handleCardResult(result: Result<StatCard>, viewHolder: AnalyseTabContract.CardView) {

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

        analyseAdapter.SetPresenter(this)

        // If we have a champ, include it. If we dont, don include it.
        if (champ != null) {
            analyseInteractor.RequestFilterList(role, champ!!.champId, this)
        } else {
            analyseInteractor.RequestFilterList(role, this)
        }
    }

    override fun setRole(role: Int) {
        this.role = role
    }

    override fun setChamp(champ: Champ) {
        this.champ = champ
    }

    override fun setPlaceHolder(string: String) {
        view.SetPlaceHolderVisible()
        view.SetPlaceHolderString(string)
    }

    override fun onCardBinding(viewHolder: AnalyseTabContract.CardView, position: Int) {
        // load the data. on the result, fetch from
        val statUrl = statList.stats[position]
        viewHolder.setTitle(statUrl.title)
        analyseInteractor.loadIndividualStat("", -1, viewHolder,this)
    }

    override fun handleItemClick(position: Int) {
        val filter = filterList[position]
        // launch new activtiy with the value.
    }
}
