package com.example.modulebanktest.features.currencies.di

import com.example.modulebanktest.features.currencies.data.api.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class CurrencyFeaturesModule {

    @Provides
    fun provideCurrencyApi(retrofit: Retrofit) = retrofit.create(
        CurrencyApi::class.java
    )
}