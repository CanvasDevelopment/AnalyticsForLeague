package com.teamunemployment.lolanalytics

import android.app.Application
import android.test.ApplicationTestCase

import org.junit.Assert

import org.junit.Test

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
class ApplicationTest : ApplicationTestCase<Application>(Application::class.java) {

    @Test
    fun testThatWeCanAdd2Plus2() {
        Assert.assertEquals(2, 2)
    }
}