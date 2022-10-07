package com.blueberryprojects.xchange.di

import android.app.Application
import androidx.room.Room
import com.blueberryprojects.xchange.common.Constants.BASE_URL
import com.blueberryprojects.xchange.featurexchange.data.datasource.BalanceDao
import com.blueberryprojects.xchange.featurexchange.data.datasource.ExchangeDatabase
import com.blueberryprojects.xchange.featurexchange.data.remote.ExchangeRateApi
import com.blueberryprojects.xchange.featurexchange.domain.repository.BalanceRepository
import com.blueberryprojects.xchange.featurexchange.domain.repository.CurrencyRepository
import com.blueberryprojects.xchange.featurexchange.domain.usecase.GetAllBalancesUseCase
import com.blueberryprojects.xchange.featurexchange.domain.usecase.GetCurrencyExchangeRate
import com.blueberryprojects.xchange.featurexchange.domain.usecase.UseCasesExchange
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExchangeDatabase(app: Application): ExchangeDatabase {
        return Room.databaseBuilder(
            app,
            ExchangeDatabase::class.java, "db_exchange")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRateApi(): ExchangeRateApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUseCasesExchange(balanceRepository: BalanceRepository, currencyRepository: CurrencyRepository) = UseCasesExchange(
        GetAllBalancesUseCase(balanceRepository),
        GetCurrencyExchangeRate(currencyRepository)
    )

    @Provides
    @Singleton
    fun provideBalanceDao(db: ExchangeDatabase): BalanceDao {
        return db.balanceDao()
    }
}
















