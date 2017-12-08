package com.teamunemployment.lolanalytics.data.model

/**
 * Created by Josiah Kendall
 */
data class Result<T>(val resultCode : Int,
                  val data : T)