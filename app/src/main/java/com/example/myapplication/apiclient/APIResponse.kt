package com.example.myapplication.apiclient

import com.squareup.moshi.JsonClass
import okhttp3.Headers
import okhttp3.HttpUrl

@JsonClass(generateAdapter = true)
data class APIResponse(
    var code: Int = 0,
    var body: String? = null,
    var isSuccessful: Boolean = false,
    var error: String? = null,
    val requestUrl: HttpUrl? = null,
    var data: Any? = null,
    var headers: Headers? = null
) {

    fun headerForName(name: String): String? {
        return headers?.get(name)
    }
}
