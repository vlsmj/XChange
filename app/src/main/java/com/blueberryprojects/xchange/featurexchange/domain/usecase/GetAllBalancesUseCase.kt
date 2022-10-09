package com.blueberryprojects.xchange.featurexchange.domain.usecase

import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance
import com.blueberryprojects.xchange.featurexchange.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllBalancesUseCase @Inject constructor(
    private val repository: BalanceRepository,
) {

    operator fun invoke(): Flow<Resource<List<Balance>>> = flow {
        repository.getAllBalances().collect {
            emit(it)
        }
    }
}