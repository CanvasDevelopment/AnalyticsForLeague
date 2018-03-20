package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.teamunemployment.lolanalytics.R
import kotlinx.android.synthetic.main.match_history_details_view.*

/**
 * Created by Josiah Kendall
 */
class DetailsView : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stat_details_view)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition( R.anim.slide_out_exit, R.anim.slide_in_exit)
    }

    /**
     * Show a message to the user in the form of a [Snackbar]
     *
     * @param message The message to display
     */
    fun showMessage(message : String) {
        Snackbar.make(base_root, message, Snackbar.LENGTH_LONG)
    }
}