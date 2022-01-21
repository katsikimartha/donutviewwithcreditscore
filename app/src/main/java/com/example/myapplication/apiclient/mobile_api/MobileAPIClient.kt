package com.example.myapplication.apiclient.mobile_api

import com.example.myapplication.apiclient.APIResponse
import com.example.myapplication.apiclient.RetrofitFactory
import com.example.myapplication.apiclient.dataclasses.CreditScoreData

class MobileAPIClient(
    private val service: RetrofitFactory.RetrofitService
) : MobileAPIClientInterface {


    override suspend fun getCreditScoreData(): APIResponse {
        val response = service.getCreditData()
        return handleResponse(response = response, outputClass = CreditScoreData::class.java)
    }

}
