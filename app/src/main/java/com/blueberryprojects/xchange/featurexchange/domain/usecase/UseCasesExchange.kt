package com.blueberryprojects.xchange.featurexchange.domain.usecase

class UseCasesExchange(
    val balancesUseCase: GetAllBalancesUseCase,
    val insertBalanceUseCase: InsertBalanceUseCase,
    val getCurrencyExchangeRate: GetCurrencyExchangeRate,
)