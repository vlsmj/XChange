package com.blueberryprojects.xchange.featurexchange.domain.repository

import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance
import kotlinx.coroutines.flow.Flow

interface BalanceRepository {

    suspend fun getAllBalances(): Flow<Resource<List<Balance>>>

    fun insertBalance(balance: Balance)
}