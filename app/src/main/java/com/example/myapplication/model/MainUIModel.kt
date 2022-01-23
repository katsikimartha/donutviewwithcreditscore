package com.example.myapplication.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.myapplication.BR
import com.example.myapplication.utils.ObservableDelegate

class MainUIModel(
    private val _creditScorePercentage: Int = 0,
    private val _creditScore: String = "",
    private val _totalScore: String = ""
) : BaseObservable() {

    @get:Bindable
    var creditScorePercentage by ObservableDelegate(BR.creditScorePercentage, _creditScorePercentage)

    @get:Bindable
    var creditScore by ObservableDelegate(BR.creditScore, _creditScore)

    @get:Bindable
    var totalScore by ObservableDelegate(BR.totalScore, _totalScore)
}