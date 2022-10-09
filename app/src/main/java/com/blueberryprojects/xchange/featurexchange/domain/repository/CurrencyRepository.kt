package com.blueberryprojects.xchange.featurexchange.domain.repository

import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.model.Rate
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getCurrencyExchangeRate(from: String, to: String, amount: Double): Flow<Resource<Rate>>
}