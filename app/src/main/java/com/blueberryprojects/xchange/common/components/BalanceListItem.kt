package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BalanceListItem(
    currency: String,
    amount: Double,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        Text(text = currency,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp)
        Text(text = String.format("%.2f", amount),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp)
    }
}