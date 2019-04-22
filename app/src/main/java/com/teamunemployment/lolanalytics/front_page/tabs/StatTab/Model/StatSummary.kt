package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model

/**
 * Created by Josiah Kendall
 *
 * @param type The cardUrl type
 * @param cardUrl The cardUrl data url
 * @param detailUrl The details url for this cardUrl
 */
data class StatSummary(val type : Int,
                       val cardUrl: String,
                       val detailUrl: String,
                       val title : String)