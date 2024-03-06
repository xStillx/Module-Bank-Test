package com.example.modulebanktest.features.currencies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Currency(
    val rates: List<Valute>
)

@Entity(tableName = "valute_table")
data class Valute(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val charCode: String,
    val value: Double,
    val symbol: String,
    val have: Double = 100.00
)