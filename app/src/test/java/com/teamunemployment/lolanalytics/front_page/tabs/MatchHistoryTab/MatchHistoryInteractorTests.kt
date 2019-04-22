package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import android.content.Context
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao
import com.teamunemployment.lolanalytics.data.room.champ.ChampDao
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import org.junit.Test
import org.mockito.Mockito.mock

class MatchHistoryInteractorTests {

    val matchHistoryInteractor = MatchHistoryInteractor(mock(Context::class.java),
            mock(RetrofitFactory::class.java),
            mock(Network::class.java),
            mock(MatchHistoryDao::class.java),
            mock(ChampDao::class.java))

    // test that we can calculate correct times
    val oneSecond : Long = 1000
    val oneMinute : Long = 60000
    val oneHour : Long = oneMinute * 60
    val oneDay : Long = oneHour * 24
    val oneWeek : Long = oneDay * 7

    @Test
    fun testThatWeCanCalculateOneWeekAgoAsAString() {
        val time = System.currentTimeMillis() - oneWeek
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "1 week ago")
    }

    @Test
    fun testThatWeCanCalculateMoreThanOneWeekAgoAsAString() {
        val time = System.currentTimeMillis() - (oneWeek*6)
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "6 weeks ago")
    }

    @Test
    fun testThatWeCanCalculateOneDayAgoAsAString() {
        val time = System.currentTimeMillis() - oneDay
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "1 day ago")
    }

    @Test
    fun testThatWeCanCalculateMoreThanOneDayAgoAsAString() {
        val time = System.currentTimeMillis() - (oneDay*4)
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "4 days ago")
    }

    @Test
    fun testThatWeCanCalculateOneHourAgoAsAString() {
        val time = System.currentTimeMillis() - oneHour
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "1 hour ago")
    }

    @Test
    fun testThatWeCanCalculateMoreThanOneHourAgoAsAString() {
        val time = System.currentTimeMillis() - (oneHour*4)
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "4 hours ago")
    }

    @Test
    fun testThatWeCanCalculateOneMinuteAgoAsAString() {
        val time = System.currentTimeMillis() - oneMinute
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "1 minute ago")
    }

    @Test
    fun testThatWeCanCalculateMoreThanOneMinuteAgoAsAString() {
        val time = System.currentTimeMillis() - (oneMinute*4)
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "4 minutes ago")
    }

    @Test
    fun testThatWeCanCalculateOneSecondAgoAsAString() {
        val time = System.currentTimeMillis() - oneSecond
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "1 second ago")
    }

    @Test
    fun testThatWeCanCalculateMoreThanOneSecondAgoAsAString() {
        val time = System.currentTimeMillis() - (oneSecond*4)
        val result =matchHistoryInteractor.produceTimestamp(time)
        assert(result == "4 seconds ago")
    }
}