package com.teamunemployment.lolanalytics.Utils;

import com.teamunemployment.lolanalytics.data.Statics;

/**
 * Created by Josiah Kendall
 */

public class RoleUtils {

    public String GetRoleName(int role) {
        switch (role) {
            case Statics.ADC:
                return "ADC";
            case Statics.JUNGLE:
                return "Jungle";
            case Statics.MID:
                return "MID";
            case Statics.SUPPORT:
                return "SUPPORT";
            case Statics.TOP:
                return "TOP";
        }
        throw new IllegalStateException("Failed to find correct role. Please set role as role defined" +
                "in Statics class");
    }
}
