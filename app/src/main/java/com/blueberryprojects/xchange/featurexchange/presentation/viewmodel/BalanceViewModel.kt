package com.blueberryprojects.xchange.featurexchange.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueberryprojects.xchange.common.Constants
import com.blueberryprojects.xchange.common.PrefManager
import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance
import com.blueberryprojects.xchange.featurexchange.domain.usecase.UseCasesExchange
import com.blueberryprojects.xchange.featurexchange.presentation.state.BalancesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val useCasesExchange: UseCasesExchange,
    private val prefManager: PrefManager,
) : ViewModel() {

    init {
        if (prefManager.isInitialLaunch) {
            insertBalance(toBalance = Balance().apply {
                currency = "EUR"
                balance = 1000.00
            })
            prefManager.isInitialLaunch = false
        }

        getAllBalances()
    }

    var balanceState = mutableStateOf(BalancesState())

    fun getCommissionFee(inputAmount: Double, currency: String): Double {
        // Free conversion up to $FREE_CONVERSION_COUNT value
        if (prefManager.freeConversionCount > 0) {
            prefManager.freeConversionCount -= 1
            return 0.00
        }

        // Free conversion if currency is selected to EUR and amount is more than or equal to 200.00
        if (currency == "EUR" && inputAmount >= 200.00) {
            return 0.00
        }

        val commissionFee = inputAmount * Constants.COMMISSION_FEE_RATE

        return commissionFee
    }

    fun getBalanceAfterFee(inputAmount: Double, commissionFee: Double): Double {
        return inputAmount - commissionFee
    }

    private fun getAllBalances() {
        useCasesExchange.balancesUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data ->
                        balanceState.value = balanceState.value.copy(
                            data = data
                        )
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun insertBalance(fromBalance: Balance? = null, toBalance: Balance) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                useCasesExchange.insertBalanceUseCase(fromBalance, toBalance)

                getAllBalances()
            }
        }
    }
}