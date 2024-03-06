package com.example.modulebanktest.features.currencies.data

import com.example.modulebanktest.features.currencies.data.api.CurrencyApi
import com.example.modulebanktest.features.currencies.data.model.mapper.CurrencyMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepo @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val currencyMapper: CurrencyMapper
) {

    suspend fun getCurrency() = withContext(Dispatchers.IO){
        currencyApi.getCurrency().let {
            currencyMapper.map(it)
        }
    }

}