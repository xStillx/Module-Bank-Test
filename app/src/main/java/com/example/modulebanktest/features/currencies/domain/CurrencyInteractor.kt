package com.example.modulebanktest.features.currencies.domain

import com.example.modulebanktest.features.currencies.data.CurrencyRepo
import com.example.modulebanktest.utils.safeRequest
import javax.inject.Inject

class CurrencyInteractor @Inject constructor(
    private val currencyRepo: CurrencyRepo
) {

    suspend fun getCurrency() = safeRequest {
        currencyRepo.getCurrency()
    }
}