package com.example.modulebanktest.features.currencies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    @Json(name = "Valute")
    val valute: ValuteResponse
)

@JsonClass(generateAdapter = true)
data class ValuteResponse(
    @Json(name = "AUD")
    val aud: ValueResponse,
    @Json(name = "USD")
    val usd: ValueResponse,
    @Json(name = "EUR")
    val eur: ValueResponse,
    @Json(name = "KZT")
    val kzt: ValueResponse,
)

@JsonClass(generateAdapter = true)
data class ValueResponse(
    @Json(name = "ID")
    val id: String,
    @Json(name = "CharCode")
    val charCode: String,
    @Json(name = "Value")
    val value: Double
)