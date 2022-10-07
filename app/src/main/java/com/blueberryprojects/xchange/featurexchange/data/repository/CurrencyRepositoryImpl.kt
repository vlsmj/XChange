package com.blueberryprojects.xchange.featurexchange.data.repository

import com.blueberryprojects.xchange.R
import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.common.util.UiText
import com.blueberryprojects.xchange.featurexchange.data.remote.ExchangeRateApi
import com.blueberryprojects.xchange.featurexchange.data.remote.dto.toRate
import com.blueberryprojects.xchange.featurexchange.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: ExchangeRateApi,
) : CurrencyRepository {

    override suspend fun getCurrencyExchangeRate(from: String, to: String, amount: Double) = flow {
        try {
            emit(Resource.Loading())

            val convertedAmount = api.convertCurrency(from, to, amount)

            emit(Resource.Success(convertedAmount.toRate()))


//            val convertedAmount = api.convertCurrency(from, to, amount)
//
//            val newBalance = Balance().apply {
//                currency = to
//                balance = convertedAmount.toConvert().result
//            }
//
//            balanceDao.insertBalance(newBalance)
//
//            emit(Resource.Success(balanceDao.getAllBalances()))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }
}