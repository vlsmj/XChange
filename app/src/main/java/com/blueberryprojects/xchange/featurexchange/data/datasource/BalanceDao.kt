package com.blueberryprojects.xchange.featurexchange.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance

@Dao
interface BalanceDao {

    @Query("SELECT * FROM balance ORDER BY currency ASC")
    suspend fun getAllBalances(): List<Balance>

    @Query("SELECT * FROM balance WHERE currency = :currency")
    fun getBalance(currency: String): Balance?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBalance(balance: Balance)

    @Query("UPDATE balance SET balance = balance - :newBalance WHERE currency = :currency")
    fun updateBalance(currency: String, newBalance: Double)
}