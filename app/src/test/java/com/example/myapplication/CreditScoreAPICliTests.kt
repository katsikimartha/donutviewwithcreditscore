package com.example.myapplication

import com.example.myapplication.apiclient.RetrofitFactory
import com.example.myapplication.apiclient.mobile_api.MobileAPIClient
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CreditScoreAPICliTests {

    private val service = mockk<RetrofitFactory.RetrofitService>()

    private lateinit var apiClient: MobileAPIClient

    @Before
    fun setup() {
        MockKAnnotations.init()
        apiClient = MobileAPIClient(service)
    }

    @Test
    fun `Test get inbox messages`() = runBlocking {
        val mockResponse = RetrofitFactory.successfulResponseFromResourceAsync("credit_score_response.json")
        coEvery { service.getCreditData() } returns mockResponse

        val response = apiClient.getCreditScoreData()
        Assert.assertEquals(true, response.isSuccessful)
    }

}