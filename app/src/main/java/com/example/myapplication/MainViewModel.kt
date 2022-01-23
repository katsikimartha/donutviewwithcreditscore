package com.example.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MainUIModel
import com.example.myapplication.usecase.GetCreditScoreUseCase
import com.example.myapplication.utils.CustomException
import com.example.myapplication.utils.Unknown
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getCreditScoreUseCase: GetCreditScoreUseCase) : ViewModel() {

    private val _exception: MutableLiveData<CustomException> = MutableLiveData()
    val exception: LiveData<CustomException>
        get() = _exception

    private val mainUIModel = MainUIModel()

    fun getMainUIModel() = mainUIModel

    fun getCreditScoreData(context: Context) {
        viewModelScope.launch {
            try {
                val data = getCreditScoreUseCase.execute(Unit)
                mainUIModel.apply {
                    creditScore = data.creditReportInfo.score.toString()
                    totalScore = data.creditReportInfo.maxScoreValue.toString()
                    creditScorePercentage = (data.creditReportInfo.score * 100) / data.creditReportInfo.maxScoreValue
                }
            } catch (error: Exception) {
                _exception.value = if (error is CustomException) {
                    error
                } else Unknown(null)
            }
        }
    }
}