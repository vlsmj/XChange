package com.blueberryprojects.xchange.featurexchange.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance

@Database(entities = [Balance::class], version = 1)
abstract class ExchangeDatabase : RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
}