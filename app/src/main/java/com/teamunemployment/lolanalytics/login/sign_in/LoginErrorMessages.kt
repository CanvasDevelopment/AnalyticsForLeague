package com.teamunemployment.lolanalytics.login.sign_in

/**
 * Created by Josiah Kendall
 */
class LoginErrorMessages {

    fun `404`() : String{
        return "Failed to find that user"
    }

    fun `500`() :String {
        return "An error occurred. Please try again later"
    }

    fun default(): String {
        return "Oops, something when wrong. Please try again"
    }
}