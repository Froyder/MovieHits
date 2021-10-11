package com.example.poplibexamapp

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object MyInterceptor : Interceptor {

    @Throws(IOException::class)

    override fun intercept(chain: Interceptor.Chain): Response {
        val finalUrl: HttpUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "ru")
            .build()
        val request: Request = chain.request().newBuilder().url(finalUrl).build()
        return chain.proceed(request)
    }
}