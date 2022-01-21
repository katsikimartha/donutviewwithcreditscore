package com.example.myapplication.apiclient.dataclasses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditScoreData (
    val accountIDVStatus: String,
    val creditReportInfo: CreditReportInfo,
    val dashboardStatus: String,
    val personaType: String,
    val coachingSummary: CoachingSummary,
    val augmentedCreditScore: String?,
)

@JsonClass(generateAdapter = true)
data class CreditReportInfo(
    val score:Int,
    val scoreBand:Int,
    val clientRef: String,
    val status: String,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val monthsSinceLastDefaulted: Int,
    val hasEverDefaulted: Boolean,
    val monthsSinceLastDelinquent: Int,
    val hasEverBeenDelinquent: Boolean,
    val percentageCreditUsed: Int,
    val percentageCreditUsedDirectionFlag: Int,
    val changedScore: Int,
    val currentShortTermDebt: Int,
    val currentShortTermNonPromotionalDebt: Int,
    val currentShortTermCreditLimit: Int,
    val currentShortTermCreditUtilisation: Int,
    val changeInShortTermDebt: Int,
    val currentLongTermDebt: Int,
    val currentLongTermNonPromotionalDebt: Int,
    val currentLongTermCreditLimit: String?,
    val currentLongTermCreditUtilisation: String?,
    val changeInLongTermDebt: Int,
    val numPositiveScoreFactors: Int,
    val numNegativeScoreFactors: Int,
    val equifaxScoreBand: Int,
    val equifaxScoreBandDescription: String,
    val daysUntilNextReport: Int
)

@JsonClass(generateAdapter = true)
data class CoachingSummary(
    val activeTodo: Boolean,
    val activeChat: Boolean,
    val selected: Boolean,
    val numberOfTodoItems: Int,
    val numberOfCompletedTodoItems: Int
)