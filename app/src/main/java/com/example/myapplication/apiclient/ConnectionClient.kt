package com.example.myapplication.apiclient

import com.example.myapplication.apiclient.mobile_api.MobileAPIClient

class ConnectionClient(
    mobileAPIBaseUrl: String,
    mapiHeaders: Map<String, String>?,
) : ConnectionClientInterface {

    private var mobileApiClient: MobileAPIClient = APIClientTools.createMobileAPIClient(
        mobileAPIBaseUrl,
        mapiHeaders
    )

    override fun updateApiClients(
        newMobileAPIUrl: String,
        mapiHeaders: Map<String, String>?
    ) {

        val updatedClient = APIClientTools.updatedMobileAPIClient(
            newMobileAPIUrl,
            mapiHeaders
        )
        updatedClient.let {
            mobileApiClient = it
        }
    }

    private fun dummySuccessResponse(): APIResponse {

        return APIResponse(
            200,
            "{}",
            true,
            null,
            null
        )
    }

    override suspend fun getCreditScoreData(): APIResponse {
        return mobileApiClient.getCreditScoreData()
    }
}