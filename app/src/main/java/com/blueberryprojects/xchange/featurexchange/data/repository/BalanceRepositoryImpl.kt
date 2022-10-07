package com.blueberryprojects.xchange.featurexchange.data.repository

import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.data.datasource.BalanceDao
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance
import com.blueberryprojects.xchange.featurexchange.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val balanceDao: BalanceDao,
) : BalanceRepository {

    override suspend fun getAllBalances(): Flow<Resource<List<Balance>>> = flow {
        emit(Resource.Success(balanceDao.getAllBalances()))
    }

    override fun insertBalance(from: Balance?, balance: Balance) {
        from?.let {
            balanceDao.updateBalance(it.currency, it.balance)
        }

        balanceDao.getBalance(balance.currency)?.let {
            balance.balance += it.balance
        }

        balanceDao.insertBalance(balance)
    }
}