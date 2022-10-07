package com.blueberryprojects.xchange.featurexchange.presentation.state

import com.blueberryprojects.xchange.featurexchange.domain.model.Balance

data class BalancesState(
    val data: List<Balance>? = mutableListOf(),
)