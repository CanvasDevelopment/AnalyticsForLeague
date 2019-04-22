package com.teamunemployment.lolanalytics.utilTests

import com.teamunemployment.lolanalytics.Utils.Role
import org.junit.Test

class RoleTests {

    val role = Role()
    @Test
    fun makeSureThatWeCanCorrectlyConvertTopSolo() {
        val result = role.produceCorrectRoleIntegerGivenRoleAndLane(role.SOLO, role.TOP_LANE)
        assert(result == role.TOP)
    }

    @Test
    fun makeSureThatWeCanCorrectlyConvertMidSolo() {
        val result = role.produceCorrectRoleIntegerGivenRoleAndLane(role.SOLO, role.MID_LANE)
        assert(result == role.MID)
    }

    @Test
    fun makeSureThatWeCanCorrectlyConvertJungle() {
        val result = role.produceCorrectRoleIntegerGivenRoleAndLane(role.NONE, role.JUNGLE_LANE)
        assert(result == role.JUNGLE)
    }

    @Test
    fun makeSureThatWeCanCorrectlyConvertADC() {
        val result = role.produceCorrectRoleIntegerGivenRoleAndLane(role.DUO_CARRY, role.BOTTOM)
        assert(result == role.ADC)
    }

    @Test
    fun makeSureThatWeCanCorrectlyConvertSup() {
        val result = role.produceCorrectRoleIntegerGivenRoleAndLane(role.DUO_SUPPORT, role.BOTTOM)
        assert(result == role.SUP)
    }
}
