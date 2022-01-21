package com.example.myapplication.apiclient

interface ConnectionClientInterface{

    suspend fun getCreditScoreData(): APIResponse

    @Deprecated("Do not use this, used for testing purposes only")
    fun updateApiClients(
        newMobileAPIUrl: String,
        mapiHeaders: Map<String, String>?
    )

}