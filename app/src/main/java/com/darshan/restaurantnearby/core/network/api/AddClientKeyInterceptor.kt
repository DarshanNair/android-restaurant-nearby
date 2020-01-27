package com.darshan.restaurantnearby.core.network.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AddClientKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url().newBuilder()
            .addQueryParameter("client_id", "C5UATRQ4YHCUKOODPTLNWC1ALUT33WNB5BR1SEVAPRW4A140")
            .addQueryParameter("client_secret", "PO00FLMNZKOETZO3IOXLQLFA53BKJIVHD3IXFVWH24YJS5SY")
            .addQueryParameter("v", "20130815")
            .build()

        val requestBuilder = original.newBuilder().url(url)

        return chain.proceed(requestBuilder.build())
    }

}