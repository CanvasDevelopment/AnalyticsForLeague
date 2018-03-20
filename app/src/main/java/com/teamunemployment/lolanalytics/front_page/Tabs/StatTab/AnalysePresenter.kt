package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import android.content.Context
import android.view.ViewGroup
import com.teamunemployment.lolanalytics.Utils.*
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_STAT
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_STAT
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.ViewProducer
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatSummary
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.FullStatCardView
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.StatCardView

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalysePresenter(private val analyseInteractor: AnalyseInteractor,
                       private val analyseAdapter: AnalyseAdapter,
                       private val roleUtils: RoleUtils,
                       private val summonerRapidAccessObject: SummonerRapidAccessObject,
                       private val context : Context) {
    private lateinit var view: AnalyseTabContract.View

    private var role = -1
    private var champ: Champ? = null
    private lateinit var statList : ArrayList<StatSummary>

    fun setStatList(statList : ArrayList<StatSummary>) {
        this.statList = statList
        // If we have data
        if (statList.size > 0) {
            analyseAdapter.setPresenter(this)
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

    /**
     * Handle the load of our cardUrl. This means figuring our what type of cardUrl it is, then from that
     * we can deduce the data type, and take the appropriate action.
     *
     * @param result        This is the data for our cardUrl. If it is a [FULL_STAT] it will be three long, if
     *                      it is a [HALF_STAT] it will only have a length of one.
     * @param viewHolder    This is our stat cardUrl View. It will be a child object - one of either the [FullStatCardView]
     *                      or the [HalfStatCardView]
     *
     */
    fun handleCardResult(result: ArrayList<HeadToHeadStat>, viewHolder: StatCardView) {
//        view.setKillsChart(result.kda.earlyGame().producePieChartData(context))
        when(viewHolder) {
            is FullStatCardView -> {
                viewHolder.setGraph1(result.earlyGame())
                viewHolder.setGraph2(result.midGame())
                viewHolder.setGraph3(result.lateGame())
            }
        }

    }

    fun getFilterListSize(): Int {
        return statList.size
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
        // if we are a add, we need to display an add
        if (!viewHolder.isAd()) {
            viewHolder.setTitle(statUrl.title)
            viewHolder.setCardPosition(position)
            analyseInteractor.loadIndividualStat(statUrl.cardUrl, viewHolder,this)
        } else  {
            // load add
        }
    }

    fun handleItemClick(position: Int) {
        val cardInfo = statList[position]
        view.launchDetailsActivity(cardInfo.detailUrl)
    }

    /**
     * Handles the creation of the create view holder.
     * Creates a [StatCardView], which can be one of
     * [HALF_AD] -      An advertisement that covers half the recycler
     * [FULL_AD] -      An advertisement that covers the whole recycler.
     * [HALF_STAT] -    A stat cardUrl with just one chart/stat.
     * [FULL_STAT] -    A stat cardUrl with three charts - an early, mid and late game
     */
    fun createViewType(viewProducer: ViewProducer, parent : ViewGroup, type : Int): StatCardView {

        when(type) {
            HALF_AD -> TODO("Still need to implement half page ads")
            FULL_AD -> TODO("Still need to impement full screen ads")
            HALF_STAT -> TODO("Still need to implement half cardUrl")
            FULL_STAT -> {
                val view = viewProducer.produceFullWidthCard(parent)
                return FullStatCardView(view,this)
            }

            else -> {
                throw IllegalStateException("Failed to produce view, view type did not match")
            }
        }

    }

    fun getItem(position: Int): StatSummary{
        return statList[position]
    }

    fun getContext(): Context {
        return context
    }


}
