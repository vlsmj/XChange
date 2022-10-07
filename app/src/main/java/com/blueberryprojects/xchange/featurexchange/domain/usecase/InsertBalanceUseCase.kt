package com.blueberryprojects.xchange.featurexchange.domain.usecase

import com.blueberryprojects.xchange.featurexchange.domain.model.Balance
import com.blueberryprojects.xchange.featurexchange.domain.repository.BalanceRepository
import javax.inject.Inject

class InsertBalanceUseCase @Inject constructor(
    private val repository: BalanceRepository,
) {

    operator fun invoke(from: Balance?, balance: Balance) {
        repository.insertBalance(from, balance)
    }
}