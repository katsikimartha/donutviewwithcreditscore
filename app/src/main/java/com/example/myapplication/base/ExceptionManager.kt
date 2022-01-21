package com.example.myapplication.base

import com.example.myapplication.apiclient.APIResponse
import com.example.myapplication.apiclient.ApiErrorResponse
import com.example.myapplication.apiclient.dataclasses.asErrorResponse
import com.example.myapplication.apiclient.dataclasses.getErrorMessage
import com.example.myapplication.utils.*
import com.example.myapplication.utils.Utils.Companion.convertStringToObject
import com.example.myapplication.utils.bus.BusEvent
import com.example.myapplication.utils.bus.LiveDataBus

class ExceptionManager(private val eventBus: LiveDataBus) : ExceptionMapper {

    override fun getException(apiResponse: APIResponse): Exception {

        val exception: Exception? = when (getErrorType(apiResponse)) {
            "Tombstone" -> {
                eventBus.publish(BusEvent.TOMBSTONE)
                TombstoneException()
            }
            "InvalidRequestException" -> {
                val errors = apiResponse.asErrorResponse()?.errors
                var remainingAttempts: Int? = 0
                errors?.forEach { error ->
                    error.data?.let { dt -> dt.remainingAttempts?.let { count -> remainingAttempts = count } }
                }
                InvalidRequest(remainingAttempts)
            }
            "ConnectionException" -> Connection()
            "InternalServerError" -> {
                eventBus.publish(BusEvent.SERVER_ISSUE)
                InternalServerError(apiResponse.getErrorMessage())
            }
            else -> null
        }

        if (exception != null) {
            return exception
        }

        return Unknown(apiResponse.getErrorMessage())
    }


    private fun getErrorType(apiResponse: APIResponse): String? {
        return try {
            apiResponse.error?.let {
                val apiErrorResponse: ApiErrorResponse? = convertStringToObject(it)
                apiErrorResponse?.getErrorType()
            }
        } catch (e: Exception) {
            handleErrorParsingFailure(apiResponse)
        }
    }

    private fun handleErrorParsingFailure(apiResponse: APIResponse) =
        when (apiResponse.code) {
            403 -> "UnauthorisedException"
            418 -> "Tombstone"
            in 500..599 -> "InternalServerError"
            705 -> "ConnectionException"
            701 -> "UnknownHostException"
            else -> ""
        }
}
