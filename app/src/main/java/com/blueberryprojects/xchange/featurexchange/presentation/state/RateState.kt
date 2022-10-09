package com.blueberryprojects.xchange.featurexchange.presentation.state

import com.blueberryprojects.xchange.featurexchange.domain.model.Rate

data class RateState(
    val isLoading: Boolean = false,
    val data: Rate? = null,
)