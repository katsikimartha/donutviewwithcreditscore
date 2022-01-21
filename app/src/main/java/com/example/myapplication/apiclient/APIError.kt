package com.example.myapplication.apiclient

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiErrorResponse(
    val status: String,
    val errors: List<Errors>) {
    fun getErrorType() = if (errors.isNotEmpty())
        errors[0].type
    else ""
}

@JsonClass(generateAdapter = true)
data class Errors(
    var type: String,
    val message: String)