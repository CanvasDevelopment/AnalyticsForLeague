package com.teamunemployment.lolanalytics.Utils

import com.teamunemployment.lolanalytics.data.Statics

/**
 * Created by Josiah Kendall
 */

class RoleUtils {

    fun getRoleName(role: Int): String {
        when (role) {
            Statics.ADC -> return "ADC"
            Statics.JUNGLE -> return "Jungle"
            Statics.MID -> return "MID"
            Statics.SUPPORT -> return "SUPPORT"
            Statics.TOP -> return "TOP"
        }
        throw IllegalStateException("Failed to find correct role. Please set role as role defined" + "in Statics class")
    }
}
