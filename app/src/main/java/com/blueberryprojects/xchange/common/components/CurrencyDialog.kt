package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CurrencyDialog(
    currencies: Array<String>,
    onCurrencyClick: (currency: String) -> Unit,
) {
    Dialog(onDismissRequest = {

    }, content = {
        Column(modifier = Modifier
            .height(320.dp)
            .background(Color.White)) {
            Text(text = "Select Currency",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
            LazyColumn {
                items(currencies) { item ->
                    CurrencyListItem(currency = item) {
                        onCurrencyClick(it)
                    }
                }
            }
        }
    })
}