package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.DetailsView
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter
import kotlinx.android.synthetic.main.tab_view_fragment.*
import org.koin.android.ext.android.inject

/**
 * Created by Josiah Kendall
*/

class AnalyseTabView : Fragment(), AnalyseTabContract.View {

    private val presenter: AnalysePresenter by inject()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater!!.inflate(R.layout.tab_view_fragment, container, false)

        val args = arguments
        val role = args.getInt(Constant.ROLE_KEY, -1)

        // Set our view. This starts the loading of value.
        presenter.setView(this)
        presenter.setRole(role)
        presenter.start()
        return rootView
    }

    override fun setPlaceHolderVisible() {
        errorMessageView.visibility = View.VISIBLE
    }

    override fun setPlaceHolderInvisible() {
        errorMessageView.visibility = View.GONE
    }

    override fun setPlaceHolderString(message: String) {
        errorMessageView.text = message
    }

    override fun setChamp(champ: Champ) {
        presenter.setChamp(champ)
        presenter.start()
    }

    override fun setRole(role: Int) {
        presenter.setRole(role)
    }

    override fun launchDetailsActivity(detailsUrl : String) {

        val detailsIntent = Intent(this.context, DetailsView::class.java)
        startActivity(detailsIntent)
        this.activity.overridePendingTransition( R.anim.slide_in_entry, R.anim.slide_out_entry)
    }

    override fun setAdapter(adapter: TabContract.TabAdapter) {
        val analyseAdapter = adapter as AnalyseAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = analyseAdapter
    }

    override fun setLoadingVisible(visible: Boolean) {

    }

    override fun showMessage(message: String) {

    }
}