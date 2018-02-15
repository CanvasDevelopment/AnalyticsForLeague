package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import android.util.Log
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

import com.teamunemployment.lolanalytics.data.Statics
import com.teamunemployment.lolanalytics.data.model.MatchSummary
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract

import java.util.ArrayList

import javax.inject.Inject
import com.github.mikephil.charting.data.PieEntry



/**
 * @author Josiah Kendall.
 */
class MatchHistoryPresenter @Inject
constructor(private val matchHistoryInteractor: MatchHistoryInteractor,
            private val summonerRapidAccessDataObject : SummonerRapidAccessObject) : MatchHistoryTabContract.Presenter {

    private var role : Int = -1
    private lateinit var view: TabContract.View

    override fun start() {
        loadDataForRole(Statics.TOP, -1)
    }

    override fun handleError(e: Throwable) {
        Log.d("MatchHistory", "Error: " + e.message)
    }

    override fun restart() {

    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    override fun loadDataForRole(role: Int, summonerId: Long) {

        matchHistoryInteractor.loadMatches("oce",summonerId,role,20/*default*/,this)
//        matchHistoryInteractor.loadCachedMatchHistoryData(role, -1, this)
//        matchHistoryInteractor.loadFreshMatchHistoryData(role, -1, this)
    }

    override fun loadMatchSummary(matchId: Long) {

//        matchHistoryInteractor.LoadCachedMatchSummary(matchId, this)
//        matchHistoryInteractor.LoadFreshMatchSummary(matchId, this)
    }

    override fun setView(view: TabContract.View) {
        this.view = view
    }

    override fun onMatchSummaryLoadedSuccessfully(matchSummary: MatchSummary) {

    }

    override fun onError(e: Throwable) {
        //        Log.e("MatchHistory", "Error: " + e.getMessage());
        view.showMessage("An error occurred. Please try again.")
    }

    override fun onMatchListLoadedSuccessfully(matchHistoryData: ArrayList<String>) {
        val matchHistoryAdapter = MatchHistoryAdapter(this)

        matchHistoryAdapter.setData(matchHistoryData)
        view.setAdapter(matchHistoryAdapter)
    }

    override fun setRole(role: Int) {
        this.role = role
    }

    /**
     * Load the card value.
     * @param id The match id for the value we want to load.
     * @param cardViewContract The card object to present the value back to.
     */
    override fun loadCardData(id : String, cardViewContract : MatchHistoryCardViewContract, region : String) {
        matchHistoryInteractor.loadMatchDetails(id.toLong(), this, cardViewContract, region,summonerRapidAccessDataObject.summonerId )
    }

    /**
     * Set our loaded card value back to the presenter for presenting to the card.
     * =====================
     * Note the cardViewContract being handed around. That is because we want to
     * maintain the same request/callback pattern that we use everywhere else for ease.
     * The reason we have to keep the instance is that we cannot reliably maintain a reference to that card
     * object any other way. The other option is to take the observables out of the interactor and process
     * them here in the presenter. I feel like this is the lesser of two evils.
     * @param matchHistoryCardData The card value to present.
     * @param cardViewContract The card view object to present to.
     */
    override fun setLoadedCardData(matchHistoryCardData: MatchHistoryCardData,
                                   cardViewContract: MatchHistoryCardViewContract) {
        cardViewContract.setHeroChampIcon("NOT_DONE" + matchHistoryCardData.champId + ".png")
        cardViewContract.setGraph1(matchHistoryCardData.earlyGame)
        cardViewContract.setGraph2(matchHistoryCardData.midGame)
        cardViewContract.setGraph3(matchHistoryCardData.lateGame)

        val enemyStat = matchHistoryCardData.earlyGame.enemyStatValue +
                matchHistoryCardData.midGame.enemyStatValue +
                matchHistoryCardData.lateGame.enemyStatValue
        val heroStat= matchHistoryCardData.earlyGame.heroStatValue +
                matchHistoryCardData.midGame.heroStatValue+
                matchHistoryCardData.lateGame.heroStatValue

        val summaryPerformance = HeadToHeadStat(enemyStat, heroStat)

        cardViewContract.setSummaryChart(summaryPerformance)

    }

    fun fetchPieDataSet(HeadToHeadStat: HeadToHeadStat): PieDataSet {
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(HeadToHeadStat.heroStatValue, "Hero"))
        entries.add(PieEntry(HeadToHeadStat.enemyStatValue, "Enemy"))

        return PieDataSet(entries, "")

//        val data = PieData(set)
//        pieChart.setData(data)
//        pieChart.invalidate() // refres
    }

    fun fetchPieData(pieDataSet: PieDataSet): PieData{
        return PieData(pieDataSet)
    }

    fun onDetailsButtonClick() {
        // launch new view with sliding animation
        view.launchDetailsActivity()
    }
}
