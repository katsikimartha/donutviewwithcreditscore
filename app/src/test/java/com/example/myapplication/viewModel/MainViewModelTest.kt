package com.example.myapplication.viewModel

import android.content.Context
import com.example.myapplication.MainViewModel
import com.example.myapplication.apiclient.dataclasses.CoachingSummary
import com.example.myapplication.apiclient.dataclasses.CreditReportInfo
import com.example.myapplication.apiclient.dataclasses.CreditScoreData
import com.example.myapplication.usecase.GetCreditScoreUseCase
import com.example.myapplication.utils.Connection
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MainViewModelTest : BaseTestViewModel() {

    private lateinit var mainViewModel: MainViewModel

    private val getCreditScoreUseCase = mockk<GetCreditScoreUseCase>()

    private lateinit var context: Context

    @Before
    override fun setup() {
        super.setup()

        mainViewModel = MainViewModel(getCreditScoreUseCase)

        coEvery { getCreditScoreUseCase.execute(Unit) } returns fakeData
        context = mockk(relaxed = true)
    }

    @Test
    fun `API call successful, receive data`() {
        runBlocking {
            //given
            coEvery { getCreditScoreUseCase.execute(Unit) } returns fakeData

            //when
            mainViewModel.getCreditScoreData(context = context)

            //then
            mainViewModel.getMainUIModel().apply {
                assertEquals(50, creditScorePercentage)
                assertEquals("100", creditScore)
                assertEquals("200", totalScore)
            }
        }
    }

    @Test
    fun `API call failed, exception was thrown`() {
        runBlocking {
            //given
            coEvery { getCreditScoreUseCase.execute(Unit) } throws Exception()

            //when
            mainViewModel.getCreditScoreData(context = context)

            //then
            Assert.assertEquals("", mainViewModel.getMainUIModel().creditScore)
            Assert.assertEquals("", mainViewModel.getMainUIModel().totalScore)
            Assert.assertEquals(0, mainViewModel.getMainUIModel().creditScorePercentage)
        }
    }

    @Test
    fun `API call failed, Connection exception was thrown`() {
        runBlocking {
            //given
            coEvery { getCreditScoreUseCase.execute(Unit) } throws Connection()

            //when
            mainViewModel.getCreditScoreData(context = context)

            //then
            Assert.assertEquals("", mainViewModel.getMainUIModel().creditScore)
            Assert.assertEquals("", mainViewModel.getMainUIModel().totalScore)
            Assert.assertEquals(0, mainViewModel.getMainUIModel().creditScorePercentage)
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