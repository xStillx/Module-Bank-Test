package com.example.modulebanktest.features.currencies.data.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modulebanktest.features.currencies.domain.model.Valute

@Database(entities = [Valute::class], version = 1, exportSchema = false)
abstract class ValuteDataBase: RoomDatabase() {

    abstract fun valuteDao(): ValuteDao

    companion object {

        private var INSTANCE: ValuteDataBase? = null

        fun getDataBase(context: Context): ValuteDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ValuteDataBase::class.java,
                    "valute_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}