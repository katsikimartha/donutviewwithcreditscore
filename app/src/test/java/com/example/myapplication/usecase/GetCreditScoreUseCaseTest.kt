package com.example.myapplication.usecase

import com.example.myapplication.apiclient.dataclasses.CoachingSummary
import com.example.myapplication.apiclient.dataclasses.CreditReportInfo
import com.example.myapplication.apiclient.dataclasses.CreditScoreData
import com.example.myapplication.repo.CreditScoreRepository
import com.example.myapplication.usecase.GetCreditScoreUseCase
import com.example.myapplication.utils.InvalidRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetCreditScoreUseCaseTest {

    private lateinit var useCase: GetCreditScoreUseCase

    private var repository: CreditScoreRepository = mockk()

    @Before
    fun setup() {
        useCase = GetCreditScoreUseCase(repository)
    }

    @Test
    fun `test get credit score returns success`() = runBlocking {
        coEvery { repository.getCreditScoreData() } returns fakeData

        val response = useCase.execute(Unit)

        coVerify { repository.getCreditScoreData() }
        assertEquals(fakeData, response)
    }

    @Test
    fun `test get credit score fails`() {
        coEvery { repository.getCreditScoreData() } throws InvalidRequest()

        try {
            runBlocking {
                useCase.execute(Unit)
                Assert.fail("Should throw InvalidRequest() exception")
            }
        } catch (error: Exception) {
            // Success
        }
    }

    private val fakeData = CreditScoreData(
        accountIDVStatus = "PASS",
        dashboardStatus = "",
        personaType = "",
        augmentedCreditScore = null,
        coachingSummary = CoachingSummary(activeTodo = false, activeChat = false, selected = false, numberOfCompletedTodoItems = 0, numberOfTodoItems = 0),
        creditReportInfo = CreditReportInfo(
            score = 100,
            scoreBand = 300,
            clientRef = "",
            status = "",
            maxScoreValue = 200,
            minScoreValue = 20,
            monthsSinceLastDefaulted = 1,
            monthsSinceLastDelinquent = 2,
            hasEverBeenDelinquent = false,
            hasEverDefaulted = false,
            changedScore = 0,
            changeInLongTermDebt = 0,
            changeInShortTermDebt = 0,
            currentLongTermCreditLimit = null,
            currentLongTermCreditUtilisation = null,
            currentLongTermDebt = 0,
            currentLongTermNonPromotionalDebt = 0,
            currentShortTermCreditLimit = 0,
            currentShortTermCreditUtilisation = 0,
            currentShortTermDebt = 0,
            currentShortTermNonPromotionalDebt = 0,
            percentageCreditUsed = 8,
            percentageCreditUsedDirectionFlag = 22,
            numNegativeScoreFactors = 2,
            numPositiveScoreFactors = 3,
            equifaxScoreBand = 2,
            equifaxScoreBandDescription = "",
            daysUntilNextReport = 3
        )
    )
}