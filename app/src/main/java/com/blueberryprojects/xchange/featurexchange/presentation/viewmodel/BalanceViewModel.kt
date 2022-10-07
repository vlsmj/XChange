package com.blueberryprojects.xchange.featurexchange.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueberryprojects.xchange.common.util.Resource
import com.blueberryprojects.xchange.featurexchange.domain.usecase.UseCasesExchange
import com.blueberryprojects.xchange.featurexchange.presentation.state.BalancesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val useCasesExchange: UseCasesExchange,
) : ViewModel() {

    var balanceState = mutableStateOf(BalancesState())

    fun getAllBalances() {
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
}