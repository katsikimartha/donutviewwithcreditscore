package com.example.myapplication.usecase

import com.example.myapplication.apiclient.dataclasses.CreditScoreData
import com.example.myapplication.base.UseCaseSuspended
import com.example.myapplication.repo.CreditScoreRepository
import javax.inject.Inject

class GetCreditScoreUseCase @Inject constructor(private val repository: CreditScoreRepository) : UseCaseSuspended<Unit, CreditScoreData> {

    override suspend fun execute(param: Unit) = repository.getCreditScoreData()

}