package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.front_page.tabs.TabContract
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.BaseActivityView
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen.DetailsView
import kotlinx.android.synthetic.main.base.*

import kotlinx.android.synthetic.main.tab_view_fragment.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 */
class MatchHistoryTabView : Fragment(), TabContract.View {


    private var rootView: View? = null

    val presenter: MatchHistoryPresenter by inject()
    val summonerRapidAccessObject : SummonerRapidAccessObject by inject()

    lateinit var baseActivityView : BaseActivityView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.tab_view_fragment, container, false)
        baseActivityView = activity as BaseActivityView
        presenter.setView(this)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter.start()
        val baseView = activity as BaseActivityView
        baseView.setCorrectRoleAndChampView()
    }

    override fun showMessage(message: String) {
         Snackbar.make(baseActivityView.base_root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun setRole(role: Int) {
        presenter.setRole(role)
        presenter.start()
    }

    override fun refresh() {
        presenter.start()
    }

    override fun setAdapter(adapter: TabContract.TabAdapter) {
        val matchHistoryAdapter = adapter as MatchHistoryRecycleViewAdapter
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager as RecyclerView.LayoutManager?
        recyclerView.adapter = matchHistoryAdapter
    }

    override fun setLoadingVisible(visible: Boolean) {

    }

    override fun launchDetailsActivity(detailsUrl : String) {
        val detailsIntent = Intent(this.context, DetailsView::class.java)
        detailsIntent.putExtra(Constant.DetailsPage.detailsUrlKey,detailsUrl)
        startActivity(detailsIntent)
        this.activity!!.overridePendingTransition( R.anim.slide_in_entry, R.anim.slide_out_entry)
    }
}
