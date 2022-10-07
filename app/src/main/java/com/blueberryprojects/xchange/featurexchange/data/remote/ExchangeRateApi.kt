package com.blueberryprojects.xchange.featurexchange.data.remote

import com.blueberryprojects.xchange.featurexchange.data.remote.dto.RateDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApi {

    @GET("convert")
    suspend fun convertCurrency(
        @Query("from") from: String = "",
        @Query("to") to: String = "",
        @Query("amount") amount: Double = 0.00,
    ): RateDto
}