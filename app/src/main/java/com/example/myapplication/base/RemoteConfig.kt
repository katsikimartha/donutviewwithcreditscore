package com.example.myapplication.base

class RemoteConfig {

    fun getFullMobileAPIBaseUrl(): String = "https://android-interview.s3.eu-west-2.amazonaws.com/"
    fun getDebugMAPIHeaders(): Map<String, String> {
        return mapOf(Pair("Content-Type:", "application/json"))
    }
}