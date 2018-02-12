package com.teamunemployment.lolanalytics.front_page

import android.util.Log

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.ncapdevi.fragnav.FragNavController
import com.teamunemployment.lolanalytics.R

import javax.inject.Inject

/**
 * @author Josiah Kendall
 */

class BaseActivityPresenter @Inject
constructor(private val baseActivityPersistenceInteractor: BaseActivityPersistenceInteractor) : BaseActivityContract.Presenter {

    private var view: BaseActivityView? = null

    private var bottomBar: AHBottomNavigation? = null

    fun setView(view: BaseActivityView) {
        this.view = view
    }

    override fun start() {

    }

    override fun handleError(e: Throwable) {
        Log.e("BaseActivityPresenter", "Error : " + e.message)
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


    override fun handleTabPress(tab: Int) {

        val summonerId: Long = -1 // TODO
        when (tab) {
            TOP -> {
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1/*todo*/, "TOP", "oce", this)
                view!!.setCorrectTabFragment(FragNavController.TAB1)
            }
            JUNGLE -> {
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1, "JUNGLE", "oce", this) // todo
                view!!.setCorrectTabFragment(FragNavController.TAB2)
            }
            MID -> {
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1, "MID", "oce", this)
                view!!.setCorrectTabFragment(FragNavController.TAB3)
            }
            MARKSMAN -> {
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1, "MARKSMAN", "oce", this)
                view!!.setCorrectTabFragment(FragNavController.TAB4)
            }
            SUPPORT -> {
                baseActivityPersistenceInteractor.fetchWinRateForRole(-1, "PLAYMAKER", "oce", this)
                view!!.setCorrectTabFragment(FragNavController.TAB5)
            }
        }
    }

    override fun onWinRateLoaded(winRate: Double) {

    }

    override fun setUpTabFragments() {
        // Do fragment setup

    }

    override fun setUpBottomBar(bottomBar: AHBottomNavigation) {
        this.bottomBar = bottomBar


        // Bottom bar items.
        val topItem = AHBottomNavigationItem("Top", R.drawable.top)
        val jgItem = AHBottomNavigationItem("Jungle", R.drawable.jg)
        val midItem = AHBottomNavigationItem("Mid", R.drawable.mid)
        val adcItem = AHBottomNavigationItem("Bottom", R.drawable.bot)
        val supItem = AHBottomNavigationItem("Support", R.drawable.sup)

        // Add items to bottom bar.
        bottomBar.addItem(topItem)
        bottomBar.addItem(jgItem)
        bottomBar.addItem(midItem)
        bottomBar.addItem(adcItem)
        bottomBar.addItem(supItem)


        // Click listener for the bottom bar buttons.
        bottomBar.setOnTabSelectedListener { position, wasSelected ->
            handleTabPress(position)
            true
        }
    }

    companion object {
        private val TOP = 0
        private val JUNGLE = 1
        private val MID = 2
        private val MARKSMAN = 3
        private val SUPPORT = 4
    }
}
