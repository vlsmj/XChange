package com.blueberryprojects.xchange.featurexchange.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance

@Dao
interface BalanceDao {

    @Query("SELECT * FROM balance ORDER BY id DESC")
    suspend fun getAllBalances(): List<Balance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: Balance)
}