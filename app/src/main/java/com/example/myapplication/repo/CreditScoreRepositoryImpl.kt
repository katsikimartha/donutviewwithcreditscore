package com.example.myapplication.repo

import com.example.myapplication.apiclient.ConnectionClientInterface
import com.example.myapplication.apiclient.dataclasses.CreditScoreData
import com.example.myapplication.base.Repository
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.ExceptionManager

interface CreditScoreRepository {
    suspend fun getCreditScoreData(): CreditScoreData
}

class CreditScoreRepositoryImpl(
    private val connectionClient: ConnectionClientInterface,
    exceptionManager: ExceptionManager,
    dispatchers: DispatcherProvider,
) : Repository(exceptionManager, dispatchers), CreditScoreRepository {

    override suspend fun getCreditScoreData() = runAsync(
        getData = { connectionClient.getCreditScoreData() },
        onSuccess = { response -> response.data as CreditScoreData }
    )

}