package com.blueberryprojects.xchange.featurexchange.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.usecase.UseCasesExchange
import com.blueberryprojects.xchange.featurexchange.presentation.state.RateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val useCasesExchange: UseCasesExchange,
) : ViewModel() {

    var rateState = mutableStateOf(RateState())

    fun getCurrencyExchangeRate(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            while (true) {
                useCasesExchange.getCurrencyExchangeRate(from, to, amount).collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            rateState.value = rateState.value.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            it.data?.let { data ->
                                rateState.value = rateState.value.copy(
                                    isLoading = false,
                                    data = data
                                )
                            }
                        }
                        else -> {}
                    }
                }
                delay(5000)
            }
        }
    }
}