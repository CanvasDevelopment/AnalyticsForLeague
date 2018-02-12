package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.DetailsView
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingView

import kotlinx.android.synthetic.main.tab_view_fragment.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 */
class MatchHistoryTabView : Fragment(), TabContract.View {


    private var rootView: View? = null
    private var role: Int = 0

    val presenter: MatchHistoryPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater!!.inflate(R.layout.tab_view_fragment, container, false)

        presenter.setView(this)

        // Top is default role
        presenter.setRole(role)
        presenter.start()
        return rootView
    }

    override fun showMessage(message: String) {
        // Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    override fun setRole(role: Int) {
        this.role = role
        presenter.setRole(role)
        presenter.start()
    }

    override fun setAdapter(adapter: TabContract.TabAdapter) {
        val matchHistoryAdapter = adapter as MatchHistoryAdapter
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = matchHistoryAdapter
    }

    override fun setLoadingVisible(visible: Boolean) {

    }

    override fun launchDetailsActivity() {
        val detailsIntent = Intent(this.context, DetailsView::class.java)
        startActivity(detailsIntent)
        this.activity.overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
    }
}
