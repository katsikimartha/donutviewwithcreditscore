package com.example.myapplication.base

interface UseCaseSuspended<in Param, out Type> {
    suspend fun execute(param: Param): Type
}