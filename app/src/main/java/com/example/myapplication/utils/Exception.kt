package com.example.myapplication.utils

import com.example.myapplication.apiclient.APIResponse

interface ExceptionMapper {
    fun getException(apiResponse: APIResponse): Exception
}

abstract class CustomException(val code: Int, val msg: String? = null, val remainingAttempts: Int? = null, val error: Throwable? = null) : Exception()

class TombstoneException : CustomException(-999)
class InvalidRequest(remainingAttempts: Int? = null) : CustomException(-1000, null, remainingAttempts)
class Connection : CustomException(-1006)
class InternalServerError(msg: String?) : CustomException(-1009, msg)

class Unknown(msg: String?) : CustomException(-2000, msg)