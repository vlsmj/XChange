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
import androidx.compose.ui.window.DialogProperties
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance

@Composable
fun CurrencyDialog(
    currencies: List<Balance>,
    onCurrencyClick: (balance: Balance) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = {
        onDismiss()
    }, properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true
    ), content = {
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
                    CurrencyListItem(balance = item) {
                        onCurrencyClick(it)
                    }
                }
            }
        }
    })
}