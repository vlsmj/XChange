package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.CurrencyViewModel

@Composable
fun MainScreen(
    currencyViewModel: CurrencyViewModel = hiltViewModel(),
) {
    val rateState = currencyViewModel.rateState.value

    Column {
        rateState.data?.let {
            Text(text = "${it.result}")
        }

        Button(onClick = {
            currencyViewModel.getCurrencyExchangeRate("PHP", "USD", 1.00)
        }) {
            Text("Exchange")
        }
    }
}