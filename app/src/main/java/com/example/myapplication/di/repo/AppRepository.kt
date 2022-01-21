package com.example.myapplication.di.repo

import com.example.myapplication.apiclient.ConnectionClientInterface
import com.example.myapplication.base.Repository
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.ExceptionManager
import javax.inject.Inject

interface AppRepository {

    fun updateAPIServer(
        newMobileAPIUrl: String,
        mapiHeaders: Map<String, String>?
    )
}

class AppRepositoryImp @Inject constructor(
    private val connectionClient: ConnectionClientInterface,
    exceptionManager: ExceptionManager,
    dispatchers: DispatcherProvider,
) : Repository(exceptionManager, dispatchers), AppRepository {

    override fun updateAPIServer(
        newMobileAPIUrl: String,
        mapiHeaders: Map<String, String>?
    ) = connectionClient.updateApiClients(
        newMobileAPIUrl,
        mapiHeaders
    )

}