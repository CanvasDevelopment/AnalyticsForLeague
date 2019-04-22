//package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab
//
//import com.teamunemployment.lolanalytics.data.model.Result
//import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.ArrayListWrapper
//import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
//import okhttp3.OkHttpClient
//import org.koin.android.ext.android.inject
//import retrofit2.Call
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
///**
// * @author Josiah Kendall
// */
//class MockMatchHistoryService() : MatchHistoryService{
//
//
////    override fun fetchMatches(numberOfMatches: Int, summonerId : String, role: Int): Call<Result<ArrayListWrapper<String>>> {
////        val retrofit : Retrofit = Retrofit.Builder()
////                .addConverterFactory(GsonConverterFactory.create())
////                .client(OkHttpClient
////                        .Builder()
////                        .addInterceptor())
////                .baseUrl("") // "https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/"
////                .build()
////
////        val service = retrofit.create(MatchHistoryService::class.java)
//////        service.
////    }
////
////    override fun fetchMatches(numberOfMatches: Int, summonerId : String, role: Int, champId: Int) {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////    }
//
//}