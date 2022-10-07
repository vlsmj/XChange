package com.blueberryprojects.xchange.featurexchange.domain.usecase

class UseCasesExchange(
    val balancesUseCase: GetAllBalancesUseCase,
    val getCurrencyExchangeRate: GetCurrencyExchangeRate,
)