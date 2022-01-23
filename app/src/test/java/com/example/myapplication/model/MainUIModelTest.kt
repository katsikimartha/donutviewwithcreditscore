package com.example.myapplication.model

import org.junit.Test
import kotlin.test.assertEquals

class MainUIModelTest {

    private val model = MainUIModel(37, "300", "800")

    @Test
    fun getCreditScore() {
        assertEquals("300", model.creditScore)
    }

    @Test
    fun getTotalScore() {
        assertEquals("800", model.totalScore)
    }

    @Test
    fun getCreditScorePercentage() {
        assertEquals(37, model.creditScorePercentage)
    }
}