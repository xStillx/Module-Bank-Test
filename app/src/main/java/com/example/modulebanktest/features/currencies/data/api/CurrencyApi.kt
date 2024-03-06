package com.example.modulebanktest.features.currencies.data.api

import com.example.modulebanktest.features.currencies.data.model.CurrencyResponse
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrency(): CurrencyResponse
}