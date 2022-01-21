package com.example.myapplication.apiclient

import com.example.myapplication.apiclient.mobile_api.MobileAPIClient
import okhttp3.Interceptor

object APIClientTools {

    fun createMobileAPIClient(
        baseUrl: String,
        mapiHeaders: Map<String, String>?
    ): MobileAPIClient {
        val apiService = RetrofitFactory
            .makeRetrofitService(
                baseUrl,
                extraInterceptors(mapiHeaders)
            )

        return MobileAPIClient(apiService)
    }

    fun updatedMobileAPIClient(
        baseUrl: String,
        mapiHeaders: Map<String, String>?
    ): MobileAPIClient {
        return createMobileAPIClient(
            baseUrl,
            mapiHeaders
        )
    }


    private fun extraInterceptors(mapiHeaders: Map<String, String>?): List<Interceptor> {

        val interceptors = mutableListOf<Interceptor>()
        interceptors.add(
            Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                )
            }
        )

        if (mapiHeaders != null) {
            val mockInterceptor = Interceptor { chain ->
                val builder = chain.request().newBuilder()

                mapiHeaders.forEach { (key, value) ->
                    builder.addHeader(key, value)
                }

                chain.proceed(builder.build())
            }

            interceptors.add(mockInterceptor)
        }

        return interceptors
    }
}