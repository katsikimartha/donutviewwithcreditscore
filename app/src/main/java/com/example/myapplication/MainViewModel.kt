package com.example.myapplication

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.apiclient.dataclasses.CreditScoreData
import com.example.myapplication.usecase.GetCreditScoreUseCase
import com.example.myapplication.utils.CustomException
import com.example.myapplication.utils.Unknown
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getCreditScoreUseCase: GetCreditScoreUseCase): ViewModel() {

    private val _creditScore: MutableLiveData<Int> = MutableLiveData()
    val creditScore: LiveData<Int>
        get() = _creditScore

    private val _exception: MutableLiveData<CustomException> = MutableLiveData()
    val exception: LiveData<CustomException>
        get() = _exception

    fun getCreditScoreData() {
        viewModelScope.launch {
            try {
                val data = getCreditScoreUseCase.execute(Unit)
                handleFetchedData(data)
            } catch (error: Exception) {
                _exception.value = if(error is CustomException) { error } else Unknown(null)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleFetchedData(data: CreditScoreData) {
        val totalScore = data.creditReportInfo.maxScoreValue
        _creditScore.value = (data.creditReportInfo.score * 100)/ totalScore
    }
}