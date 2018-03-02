package com.teamunemployment.lolanalytics.ExtensionTests

import com.teamunemployment.lolanalytics.Utils.produceCentreText
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Created by Josiah Kendall
 */
class GraphExtensions {

    @Test
    fun `Test that we can calculate center text value`() {
        val result = produceCentreText(60f, 40f)
        assertTrue(result == 60)
    }

    @Test
    fun `Make sure that we can calculate the integer correctly even when we have float values`() {
        val result = produceCentreText(84.23f, 81.43f)
        assertTrue(result == 51) // correct answer is 50.845 but we are converting to an int
    }

}