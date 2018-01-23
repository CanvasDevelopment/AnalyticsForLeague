package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab

import android.content.Context
import android.util.Log

import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatCollection
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatDefinitionWrapper
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract
import com.teamunemployment.lolanalytics.Model.Contracts.StatLoadingContract
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseTabContract
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatCard

import javax.inject.Inject

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Josiah Kendall.
 */
class PlayerAnalysisPresenter @Inject
constructor(private val playerAnalysisPersistanceInteracter: PlayerAnalysisPersistanceInteracter,
            private val context: Context) : PlayerAnalysisContract.Presenter, StatLoadingContract.StatCollectionContract {
    private lateinit var view: TabContract.View
    private var ROLE = -1

    /**
     * Logic to how we load the initial value for the view.
     */
    override fun start() {

        if (ROLE == -1) {
            throw IllegalStateException("Role must be set before calling start()")
        }

    }

    override fun handleError(e: Throwable) {
        Log.d("Analysis", "Error: " + e.message)
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

    override fun LoadStatList(role: Int, summonerId: Long) {

    }

    fun setRole(role: Int) {
        ROLE = role
    }

    override fun LoadStatDetails(statId: Int, summonerId: Long, cardViewContract: PlayerAnalysisCardViewContract) {
        val statCollectionSubscriber = getStatCollectionObserver(cardViewContract)

    }


    /**
     * Load the details that we see before opening the card.
     * @param id
     */
    fun LoadFaceDetails(id: Int) {

    }

    private fun getStatCollectionObserver(cardViewContract: PlayerAnalysisCardViewContract): Observer<StatCollection> {
        return object : Observer<StatCollection> {

            override fun onError(e: Throwable) {
                // do error
                Log.d("Analysis", "Error: " + e.message)
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(statData: StatCollection) {
                cardViewContract.setStatName(statData.statSummary.statName)
                cardViewContract.setStatSubTitle(statData.statSummary.subName)
                cardViewContract.setImprovementValue(statData.statSummary.improvementValue)
                //cardViewContract.setStatCollection(statData.getCollection());
                cardViewContract.setStatIcon("NOTDONEYET")
                cardViewContract.setStatCollection(statData.collection, statData.enemyCollection)
            }
        }
    }

    fun handleListResult(result : Result<StatList>) {

    }

    fun handleCardResult(result: Result<StatCard>, viewHolder: AnalyseTabContract.CardView) {
        // set the details correctly
    }

    override fun setView(view: TabContract.View) {
        this.view = view
    }

    override fun onStatCollectionLoaded(collection: StatCollection) {

    }

    override fun onStatCollectionFailed(message: String) {
        view!!.showMessage(message)
    }
}
