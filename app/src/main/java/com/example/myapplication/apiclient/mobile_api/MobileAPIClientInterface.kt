package com.example.myapplication.apiclient.mobile_api

import com.example.myapplication.apiclient.APIResponse
import com.example.myapplication.apiclient.dataclasses.InitialResponse
import com.squareup.moshi.Moshi
import retrofit2.Response

interface MobileAPIClientInterface {

    companion object {
        const val EMPTY_DATA_ERROR_CODE = 702
    }

    suspend fun getCreditScoreData(): APIResponse

    fun handleResponse(response: Response<String>?, outputClass: Class<out Any>): APIResponse {

        return handleResponse(response, outputClass, true)
    }

    fun handleResponse(response: Response<String>?, outputClass: Class<out Any>, hasWrapper: Boolean): APIResponse {

        if (response == null) {
            return APIResponse(
                code = EMPTY_DATA_ERROR_CODE,
                isSuccessful = false,
                error = "No Response received"
            )
        }

        val apiResponse = APIResponse(
            code = response.code(),
            body = response.body(),
            headers = response.headers(),
            requestUrl = response.raw().request.url
        )
        if (!response.isSuccessful) {

            apiResponse.isSuccessful = false
            apiResponse.error = response.errorBody()?.string()
            return apiResponse
        }
        val body = apiResponse.body
        if (body == null || body.isEmpty()) {
            return if (response.code() == 204 || response.code() == 201) {
                // 204 response code can have null body and still be valid
                apiResponse.isSuccessful = true
                apiResponse
            } else {
                apiResponse.isSuccessful = false
                apiResponse.error = "No response body received"
                apiResponse
            }
        }

        try {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(outputClass)
            apiResponse.data = adapter.fromJson(body)
            //for when the data are coming in the form of "data":{}
            /*if (hasWrapper) {
                val item = apiResponse.data as InitialResponse?
                apiResponse.data = item?.data
            }*/

            apiResponse.isSuccessful = true

        } catch (e: Exception) {

            apiResponse.isSuccessful = false
            apiResponse.error = "Unable to parse JSON response: ${e.localizedMessage}"
        }

        return apiResponse
    }
}
