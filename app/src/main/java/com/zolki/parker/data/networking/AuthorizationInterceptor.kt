package com.zolki.parker.data.networking

import com.zolki.parker.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    companion object {
        private const val HEADER_AUTHORIZATION = "X-Access-Token"
        private const val TOKEN_EMPTY = ""
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.GOLEMIO_KEY

        val request = if (token != TOKEN_EMPTY) {
            chain.request()
                .newBuilder()
                .header(HEADER_AUTHORIZATION, token)
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}