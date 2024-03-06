package com.example.modulebanktest.features.currencies.data.model.mapper

import com.example.modulebanktest.features.currencies.data.model.CurrencyResponse
import com.example.modulebanktest.features.currencies.domain.model.Currency
import com.example.modulebanktest.features.currencies.domain.model.Valute
import javax.inject.Inject

class CurrencyMapper @Inject constructor() {

    fun map(currencyResponse: CurrencyResponse) = Currency(
        rates = listOf(
            Valute(
                currencyResponse.valute.aud.id,
                currencyResponse.valute.aud.charCode,
                currencyResponse.valute.aud.value,
                symbol = "A\$"
            ),
            Valute(
                currencyResponse.valute.usd.id,
                currencyResponse.valute.usd.charCode,
                currencyResponse.valute.usd.value,
                symbol = "\$"
            ),
            Valute(
                currencyResponse.valute.eur.id,
                currencyResponse.valute.eur.charCode,
                currencyResponse.valute.eur.value,
                symbol = "€"
            ),
            Valute(
                currencyResponse.valute.kzt.id,
                currencyResponse.valute.kzt.charCode,
                currencyResponse.valute.kzt.value,
                symbol = "₸"
            )
        )
    )
}