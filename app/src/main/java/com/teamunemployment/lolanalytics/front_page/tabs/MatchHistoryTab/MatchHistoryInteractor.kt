package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import android.content.Context
import android.util.Log
import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.Utils.Role
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.Utils.getMatchHistoryCardData
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao
import com.teamunemployment.lolanalytics.data.room.champ.ChampDao

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import retrofit2.Call
import retrofit2.Response
import ru.gildor.coroutines.retrofit.await
import kotlin.collections.ArrayList

/**
 * TODO refactor this class to not have hardcoded observers.
 * @author Josiah Kendall
 */
class MatchHistoryInteractor(private val context: Context,
                             private val retrofitFactory: RetrofitFactory,
                             private val network: Network,
                             private val matchHistoryDao: MatchHistoryDao,
                             private val champDao: ChampDao) {
    private val TAG = "MatchHistoryInteractor"
    private val roleUtils = Role()
    /**
     * Load the match details to show on a card.
     * @param id The id of the match as per saved in our database. may or may not be the same as the Riot match id.
     * @param presenter The presenter to return the value to when we are done loading.
     */
    fun loadMatchDetails(id: Long,
                         role : Int,
                         presenter : MatchHistoryTabContract.Presenter,
                         cardViewContract : MatchHistoryCardViewContract,
                         region: String,
                         summonerId : String,
                         timestamp : Long) {
        async {
            val url = network.getUrlForRegion(region)
            val matchHistoryService = retrofitFactory.produceRetrofitInterface(MatchHistoryService::class.java, url)
            val call: Call<Result<MatchHistoryCardData>> = matchHistoryService.fetchMatchSummary(role,id,summonerId)
            Log.d(TAG, "Fetching match card with role $role and id $id and summonerId $summonerId")

            val response: Response<Result<MatchHistoryCardData>> = await { call.execute() }
            Log.d(TAG, "Result was : ${response.code()}")
            Log.v(TAG, "Body was : ${response.body()}")

            val result: Result<MatchHistoryCardData> = response.getMatchHistoryCardData()
            val code = result.resultCode
            if (code ==200) {
                Log.d(TAG, "Found data : ${result.data}")
                await { matchHistoryDao.insert(result.data) }
                val champ = await { champDao.loadChamp(result.data.champId.toString(), summonerId) }
                if (champ != null) {
                    presenter.setLoadedCardData(result.data, cardViewContract, timestamp, "${produceResult(result.data.won)} ${champ.title}" )
                } else {
                    presenter.setLoadedCardData(result.data, cardViewContract, timestamp, "")
                }
            }
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

    private fun produceResult(win : Boolean) : String {
        return if (win) {
            "Win as "
        } else "Loss as "
    }

    fun loadMatches(region: String,
                    summonerId : String,
                    role: Int,
                    startingPoint: Int,
                    presenter: MatchHistoryPresenter) {
        val laneString = roleUtils.produceCorrectLaneStringForRoleInt(role)
        val roleString = roleUtils.produceCorrectRoleStringForRoleInt(role)
        async {
            // try fetch from cache initially

            val matchIdentifierList = await{ matchHistoryDao.loadTwentyMatchIdenfifiers(summonerId, roleString, laneString, startingPoint)}
            if (matchIdentifierList.isEmpty()) {
                val url = network.getUrlForRegion(region)
                val matchHistoryService = retrofitFactory.produceRetrofitInterface(MatchHistoryService::class.java, url)
                val result: Result<ArrayList<MatchIdentifier>> = await{matchHistoryService.fetchMatches(role, startingPoint,summonerId).execute().body()!!}
                await{cacheMatchList(result.data)}
                presenter.onMatchListLoadedSuccessfully(result.data, result.resultCode)
            } else {
                presenter.onMatchListLoadedSuccessfully(matchIdentifierList as java.util.ArrayList<MatchIdentifier>,200)
            }
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

    fun loadMatches(region: String,
                    summonerId : String,
                    role: Int,
                    champId : Int,
                    startingPoint: Int,
                    presenter: MatchHistoryPresenter) {
        val laneString = roleUtils.produceCorrectLaneStringForRoleInt(role)
        val roleString = roleUtils.produceCorrectRoleStringForRoleInt(role)
        async {
            // try fetch from cache initially
            val matchIdentifierList = await{matchHistoryDao.loadTwentyMatchIdenfifiers(summonerId,roleString, laneString, champId, startingPoint)}
            if (matchIdentifierList.isEmpty()) {
                val url = network.getUrlForRegion(region)
                val matchHistoryService = retrofitFactory.produceRetrofitInterface(MatchHistoryService::class.java, url)
                val result: Result<ArrayList<MatchIdentifier>> = await{matchHistoryService.fetchMatchesWithChamp(role, champId, startingPoint,summonerId).execute().body()!!}
                await{cacheMatchList(result.data)}
                presenter.onMatchListLoadedSuccessfully(result.data, result.resultCode)
            } else {
                presenter.onMatchListLoadedSuccessfully(matchIdentifierList as java.util.ArrayList<MatchIdentifier>,200)
            }
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

//    fun loadMatches

    private fun cacheMatchList(data: ArrayList<MatchIdentifier>) {
        for (matchIdentifier in data) {
            matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier)
        }
    }

    fun produceTimestamp(timestamp: Long): String {
        val oneSecond : Long = 1000
        val oneMinute : Long = 60000
        val oneHour : Long = oneMinute * 60
        val oneDay : Long = oneHour * 24
        val oneWeek : Long = oneDay * 7

        val currentTimeMillis = System.currentTimeMillis()
        val diff : Long = currentTimeMillis - timestamp
        if (diff < oneMinute) {
            val numberOfSeconds : Int = (diff / oneSecond).toInt()
            if (numberOfSeconds > 1) {
                return "$numberOfSeconds seconds ago"
            }
            return "$numberOfSeconds second ago"
        }

        if (diff >= oneMinute && diff < oneHour) {
            // convert to minutes case
            val numberOfMinutes : Int = (diff / oneMinute).toInt()
            if (numberOfMinutes > 1) {
                return "$numberOfMinutes minutes ago"
            }
            return "$numberOfMinutes minute ago"

        }
        else if (diff <oneDay) {
            val numberOfHours : Int = (diff / oneHour).toInt()
            if (numberOfHours > 1) {
                return "$numberOfHours hours ago"
            }
            return "$numberOfHours hour ago"
        } else if (diff < oneWeek) {
            val numberOfDays : Int = (diff / oneDay).toInt()
            if (numberOfDays > 1) {
                return "$numberOfDays days ago"
            }
            return "$numberOfDays day ago"
        } else {
            val numberOfWeeks : Int = (diff / oneWeek).toInt()
            if (numberOfWeeks > 1) {
                return "$numberOfWeeks weeks ago"
            }
            return "$numberOfWeeks week ago"
        }
    }

    fun loadChampName(champId: Long,
                      cardViewContract: MatchHistoryCardViewContract,
                      summonerId : String,
                      win : Boolean) {
        async {
            val champ = await { champDao.loadChamp(champId.toString(), summonerId) }
            if (champ != null) {
                cardViewContract.setChamp(champ.title)
            }
        }
    }
}
