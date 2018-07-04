package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

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
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import kotlinx.android.synthetic.main.tab_view_fragment.*
import org.koin.android.ext.android.inject

/**
 * Created by Josiah Kendall
*/

class AnalyseTabView : Fragment(), AnalyseTabContract.View {

    private val presenter: AnalysePresenter by inject()
    private val rapidAccessObject : SummonerRapidAccessObject by inject()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater!!.inflate(R.layout.tab_view_fragment, container, false)

        val args = arguments
        val role = args.getInt(Constant.ROLE_KEY, -1)

        // Set our view. This starts the loading of value.
        presenter.setView(this)
        presenter.setRole(rapidAccessObject.role)
        presenter.setSummonerId(rapidAccessObject.summonerId)
        presenter.start()
        return rootView
    }

    override fun setAdapter(adapter: AnalyseAdapter) {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
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

    override fun launchDetailsActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAdapter(adapter: TabContract.TabAdapter) {

    }

    override fun setLoadingVisible(visible: Boolean) {

    }

    override fun showMessage(message: String) {

    }
}