package com.blueberryprojects.xchange.di

import com.blueberryprojects.xchange.featurexchange.data.repository.BalanceRepositoryImpl
import com.blueberryprojects.xchange.featurexchange.data.repository.CurrencyRepositoryImpl
import com.blueberryprojects.xchange.featurexchange.domain.repository.BalanceRepository
import com.blueberryprojects.xchange.featurexchange.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBalanceRepositoryImpl(
        balanceRepositoryImpl: BalanceRepositoryImpl,
    ): BalanceRepository

    @Binds
    @Singleton
    abstract fun bindCurrencyRepositoryImpl(
        currencyRepositoryImpl: CurrencyRepositoryImpl,
    ): CurrencyRepository
}