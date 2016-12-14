package com.teamunemployment.lolanalytics.mock;

import android.content.Context;
import android.net.wifi.WifiConfiguration;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Josiah Kendall.
 *
 * Used to create mock responses from retrofit, so that we can test out our
 * the consumption of our server methods in terms of response -> object.
 */

public class MockHttpClient implements Interceptor {

        private String content;
        private int code;

        public MockHttpClient(Context ctx, String content, int code) {
            this.content = content;
            this.code = code;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String mimeType = "application/json";

            return new Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(code)
                    .body(ResponseBody.create(MediaType.parse(mimeType), content))
                    .build();
        }
}