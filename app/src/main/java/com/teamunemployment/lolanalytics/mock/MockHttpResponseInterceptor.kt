package com.teamunemployment.lolanalytics.mock

import android.content.Context

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * @author Josiah Kendall.
 *
 * Used to create mock responses to retrofit, so that we can test out our
 * the consumption of our server methods in terms of response -> object.
 */
class MockHttpResponseInterceptor(var content: String, val code: Int) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val mimeType = "application/json"

        return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message(content)
                .body(ResponseBody.create(MediaType.parse(mimeType), content))
                .build()
    }
}
