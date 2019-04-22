package com.teamunemployment.lolanalytics.Utils

import com.teamunemployment.lolanalytics.data.Statics.SUPPORT

class Role {

    // ROLES
    val SOLO = "SOLO"
    val NONE = "NONE"
    val DUO_CARRY = "DUO_CARRY"
    val DUO_SUPPORT = "DUO_SUPPORT"

    // LANES
    val TOP_LANE = "TOP"
    val MID_LANE = "MID"
    val BOTTOM = "BOTTOM"
    val JUNGLE_LANE = "JUNGLE"

    // integer rep of role/lane combo
    val ADC = 4
    val SUP = 5
    val MID = 3
    val JUNGLE = 2
    val TOP = 1
    val UNSUPPORTED = -1

    fun produceCorrectRoleIntegerGivenRoleAndLane(role : String, lane :String) : Int{

        when(role) {
            SOLO -> return handleSolo(lane)
            DUO_CARRY -> return handleCarry(lane)
            DUO_SUPPORT -> return handleSupport(lane)
            NONE -> return handleJungle(lane)
        }
        return UNSUPPORTED
    }

    fun produceCorrectRoleStringForRoleInt(role : Int) : String {
        when(role) {
            TOP-> return SOLO
            MID -> return SOLO
            SUP -> return DUO_SUPPORT
            ADC -> return DUO_CARRY
            JUNGLE -> return NONE
        }

        // maybe log this as a nissue? should never happen?
        return ""
    }


    fun produceCorrectLaneStringForRoleInt(role : Int) : String {
        when(role) {
            TOP-> return TOP_LANE
            MID -> return MID_LANE
            SUP -> return BOTTOM
            ADC -> return BOTTOM
            JUNGLE -> return JUNGLE_LANE
        }
        // maybe log this as a nissue? should never happen?
        return ""
    }

    // TODO should probably fix this.
    fun produceSingleLaneStringForRoleInt(role : Int) : String {
        when (role) {
            TOP-> return TOP_LANE
            MID -> return MID_LANE
            SUP -> return "SUPPORT"
            ADC -> return "ADC"
            JUNGLE -> return JUNGLE_LANE
        }
        return ""
    }

    private fun handleSolo(lane : String) : Int{
        when (lane) {
            TOP_LANE -> return TOP
            MID_LANE -> return MID
        }
        return UNSUPPORTED
    }

    private fun handleCarry(lane : String) : Int {
        if (lane == BOTTOM) {
            return ADC
        }
        return UNSUPPORTED
    }

    private fun handleSupport(lane: String) : Int {
        if (lane == BOTTOM) {
            return SUP
        }
        return UNSUPPORTED
    }

    private fun handleJungle(lane: String) : Int {
        if (lane == JUNGLE_LANE) {
            return JUNGLE
        }
        return UNSUPPORTED
    }
}