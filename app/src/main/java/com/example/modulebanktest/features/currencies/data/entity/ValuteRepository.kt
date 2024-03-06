package com.example.modulebanktest.features.currencies.data.entity

import androidx.lifecycle.LiveData
import com.example.modulebanktest.features.currencies.domain.model.Valute

class ValuteRepository(private val valuteDao: ValuteDao) {

    val readAllData: LiveData<List<Valute>> = valuteDao.readData()

    suspend fun addValute(valute: Valute){
        valuteDao.addValute(valute)
    }

    suspend fun updateValute(valute: Valute){
        valuteDao.updateValute(valute)
    }
}