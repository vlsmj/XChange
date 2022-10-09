package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance

@Composable
fun CurrencyListItem(
    balance: Balance,
    onCurrencyClick: (balance: Balance) -> Unit,
) {
    Box(Modifier
        .clickable {
            onCurrencyClick(balance)
        }
        .padding(16.dp)) {
        Text(text = balance.currency,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)
    }
}