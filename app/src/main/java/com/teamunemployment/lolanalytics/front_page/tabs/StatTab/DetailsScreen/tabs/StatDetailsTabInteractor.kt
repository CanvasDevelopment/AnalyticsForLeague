package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsData
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import ru.gildor.coroutines.retrofit.await

class StatDetailsTabInteractor(val retrofitFactory: RetrofitFactory,
                               val network: Network) {
    private val region = "oce"
    private val statDetailsTabService: StatDetailsTabService = retrofitFactory.produceRetrofitInterface(StatDetailsTabService::class.java, network.getUrlForRegion(region))

    fun loadStats(summonerId : String,
             games : Int,
             lane : String,
             statName : String,
             presenter : StatDetailsTabPresenter) {

        async {
            val result : Result<StatDetailsData> = await{statDetailsTabService.fetchMatches(summonerId,
                    games,
                    lane,
                    statName).execute().body()!!}
            if (result.resultCode == 200) {
                presenter.handleAverage(result.data.statAvg)
                presenter.handleMaximum(result.data.statMax)
                presenter.handleMinimum(result.data.statMin)
                presenter.handleRawLineData(result.data.statHistory)
            }

        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

    fun loadStats(summonerId : String,
                  games : Int,
                  lane : String,
                  statName : String,
                  champKey : Int,
                  presenter : StatDetailsTabPresenter) {

        async {
            val result : Result<StatDetailsData> = await{statDetailsTabService.fetchMatches(summonerId,
                    games,
                    lane,
                    statName,
                    champKey.toString()).execute().body()!!}
            if (result.resultCode == 200) {
                presenter.handleAverage(result.data.statAvg)
                presenter.handleMaximum(result.data.statMax)
                presenter.handleMinimum(result.data.statMin)
                presenter.handleRawLineData(result.data.statHistory)
            }

        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

}