package com.example.modulebanktest.features.currencies.data.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.modulebanktest.features.currencies.domain.model.Valute

@Dao
interface ValuteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addValute(valute: Valute)

    @Query("SELECT * FROM valute_table ORDER BY id ASC")
    fun readData(): LiveData<List<Valute>>

    @Update(entity = Valute::class)
    fun updateValute(valute: Valute)
}