package com.example.myapplication.base

import com.example.myapplication.apiclient.APIResponse
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.ExceptionMapper
import kotlinx.coroutines.withContext

abstract class Repository(private val exceptionMapper: ExceptionMapper, private val dispatchers: DispatcherProvider) {

    suspend fun <T> runAsync(
        getCache: () -> T? = { null },
        getData: suspend () -> APIResponse,
        onSuccess: (apiResponse: APIResponse) -> T
    ) = withContext(dispatchers.io) {
        val cache = getCache()
        if (cache != null) {
            cache
        } else {
            val response = getData()
            if (response.isSuccessful) {
                onSuccess(response)
            } else {
                val exception = exceptionMapper.getException(response)
                throw exception
            }
        }
    }
}