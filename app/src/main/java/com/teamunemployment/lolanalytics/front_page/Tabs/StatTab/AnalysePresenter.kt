package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatOverview

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalysePresenter(private val analyseInteractor: AnalyseInteractor, private val analyseAdapter: AnalyseAdapter, private val roleUtils: RoleUtils) : AnalyseTabContract.Presenter {
    private var view: AnalyseTabContract.View? = null

    private var role = -1
    private var champ: Champ? = null
    private var filterList = ArrayList<AnalysisData>()

    override fun setStatList(statList : StatList) {

        // If we have data
        if (statList.totalNumberOfGames > 0) {
            view!!.SetAdapter(analyseAdapter)
            view!!.SetPlaceHolderInvisible()
        } else {
            // This means we have no value. Show an appropriate message to the user based on their settings.
            if (champ != null) {
                val message = String.format(
                        "No games found playing %s with %s",
                        roleUtils.GetRoleName(role),
                        champ!!.champName
                )
                setPlaceHolder(message)
            } else {
                val message = String.format(
                        "No games found playing %s",
                        roleUtils.GetRoleName(role)
                )
                setPlaceHolder(message)
            }
        }
    }

    fun handleListResult(result : Result<StatList>) {

    }

    fun handleCardResult(result: Result<StatOverview>) {

    }

    override fun GetFilterListSize(): Int {
        return filterList.size
    }

    override fun SetView(view: AnalyseTabContract.View) {
        this.view = view
    }

    override fun Start() {
        if (view == null) {
            throw IllegalStateException("Must call SetView before calling start")
        }

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

    override fun SetRole(role: Int) {
        this.role = role
    }

    override fun SetChamp(champ: Champ) {
        this.champ = champ
    }

    override fun setPlaceHolder(string: String) {
        view!!.SetPlaceHolderVisible()
        view!!.SetPlaceHolderString(string)
    }

    override fun OnCardBinding(viewHolder: AnalyseTabContract.CardView, position: Int) {
        val data = filterList[position]
        viewHolder.SetGraph(data.enemyPercentTotal, data.heroPercentTotal)
        viewHolder.SetChange(data.recentChange)
        viewHolder.SetTitle(data.title)
        viewHolder.SetItemPosition(position)
    }

    override fun HandleItemClick(position: Int) {
        val filter = filterList[position]
        // launch new activtiy with the value.
    }
}
