package com.blueberryprojects.xchange.featurexchange.domain.usecase

import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.model.Rate
import com.blueberryprojects.xchange.featurexchange.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrencyExchangeRate @Inject constructor(
    private val repository: CurrencyRepository,
) {

    operator fun invoke(from: String, to: String, amount: Double): Flow<Resource<Rate>> = flow {
        repository.getCurrencyExchangeRate(from, to, amount).collect {
            emit(it)
        }
    }
}