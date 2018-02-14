package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

/**
 * Created by Josiah Kendall
 */
class DetailsErrorMessages {

    fun `404`() : String {
        return "Oops, looks like we failed to find any data for this match :("
    }

    fun `500`() : String {
        return "Oops, something broke. Please try refreshing the page"
    }

}