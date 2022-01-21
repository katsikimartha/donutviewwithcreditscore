package com.example.myapplication.apiclient

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    interface RetrofitService {

        @GET("endpoint.json")
        suspend fun getCreditData(): Response<String>

//        @Headers("Content-Type: application/json")

    }

    // If certificatePinHash is null we don't do pinning.
    fun makeRetrofitService(
        baseUrl: String,
        extraInterceptors: List<Interceptor>? = null
    ): RetrofitService {

        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)

        extraInterceptors?.let { array ->
            array.forEach {
                builder.addInterceptor(it)
            }
        }

        val client = builder.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    fun successfulResponseFromResourceAsync(filename: String): Response<String> {
        val sampleFile = this.javaClass.getResource("/${filename}")
        val sampleContent = sampleFile?.readText() ?: ""
        return successfulResponseFromStringAsync(sampleContent)
    }

    private fun successfulResponseFromStringAsync(string: String): Response<String> {
        return Response.success(string)
    }
}
