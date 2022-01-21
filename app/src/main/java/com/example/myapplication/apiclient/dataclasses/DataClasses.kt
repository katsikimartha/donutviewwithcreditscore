package com.example.myapplication.apiclient.dataclasses

import com.example.myapplication.apiclient.APIResponse
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import java.io.IOException

interface InitialResponse {

    // All initial response data classes must implement this interface
    val data: Any
}


interface ConfigList {
    val displayName: String
    val entitlements: List<String>
}

fun APIResponse.asErrorResponse(): ErrorResponse? = with(error) {
    if (isSuccessful || this == null) {
        return@with null
    }
    val genericError = GenericError(GenericError.ErrorCodes.UNKNOWN, "error", this)
    val defaultError = ErrorResponse("error", listOf(genericError))
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
    return@with try {
        jsonAdapter.fromJson(this)
    } catch (e: Exception) {
        when (e) {
            is IOException, is JsonDataException -> defaultError
            else -> throw e
        }
    }
}

fun APIResponse.getErrorMessage(): String? {
    val errorResponse = asErrorResponse() ?: return null
    val errorCount = errorResponse.errors
    if (errorCount.isEmpty()) return null
    return errorCount[0].message
}
