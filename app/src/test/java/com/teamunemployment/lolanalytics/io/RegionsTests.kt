package com.teamunemployment.lolanalytics.io

import com.teamunemployment.lolanalytics.io.networking.Regions
import org.junit.Test

/**
 * Created by Josiah Kendall
 */
class RegionsTests {

    @Test
    fun `Test that we can get oce region url correctly`() {
        val regions = Regions()
        val oceUrl = regions.getRegionUrl("OCE")
        assert(oceUrl == "https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
    }
}