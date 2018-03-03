package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model

/**
 * Created by Josiah Kendall
 *
 * @param type The card type
 * @param card The card data url
 * @param detail The details url for this card
 */
data class StatSummary(val type : Int,
                       val card : String,
                       val detail : String,
                       val title : String) // todo need sto have a title