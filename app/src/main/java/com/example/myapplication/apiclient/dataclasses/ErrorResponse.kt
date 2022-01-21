package com.example.myapplication.apiclient.dataclasses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val status: String,
    val errors: List<GenericError>
)

@JsonClass(generateAdapter = true)
data class GenericError(
    val errorCode: ErrorCodes?,
    val type: String,
    val message: String,
    var data: ErrorResponseData? = null
) {
    enum class ErrorCodes {
        UNKNOWN,
        PASSCODE_CONTAINS_BIRTHDAY,
        PASSCODE_IS_REPEATING,
        PASSCODE_IS_SEQUENTIAL,
        PASSCODE_MISMATCH
    }
}

@JsonClass(generateAdapter = true)
data class ErrorResponseData(val remainingAttempts: Int?)
