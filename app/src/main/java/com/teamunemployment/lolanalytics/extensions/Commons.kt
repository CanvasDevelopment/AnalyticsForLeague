package com.teamunemployment.lolanalytics.extensions

/**
 * Created by Josiah Kendall
 */
internal inline fun <reified T : Any> objectOf() = T::class.java

internal fun getId() : Long = Math.random().toLong()