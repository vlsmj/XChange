package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.xchange.R
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.BalanceViewModel
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.CurrencyViewModel

@Composable
fun MainScreen(
    currencyViewModel: CurrencyViewModel = hiltViewModel(),
    balanceViewModel: BalanceViewModel = hiltViewModel(),
) {
    val currencies = stringArrayResource(id = R.array.array_currency_codes)

    val rateState = currencyViewModel.rateState.value
    val balanceState = balanceViewModel.balanceState.value


//    Dialog(onDismissRequest = {
//
//    }, content = {
//        Column(modifier = Modifier
//            .height(320.dp)
//            .background(Color.White)) {
//            Text(text = "Select Currency",
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp))
//            LazyColumn {
//                items(currencies) { item ->
//                    CurrencyListItem(currency = item) {
//
//                    }
//                }
//            }
//        }
//    })

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.dark_navy_blue))
            .padding(16.dp),
            contentAlignment = Alignment.Center) {
            Column {
                Text(text = "CURRENCY EXCHANGE",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(24.dp))
                Row(Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .height(100.dp)
                    .padding(16.dp)) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)) {
                        Text(text = "EUR",
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        InputTextField(modifier = Modifier
                            .fillMaxWidth(),
                            hint = "From") { input ->

                        }
                    }
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_exchange),
                            contentDescription = "exchange_icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)) {
                        Text(text = "USD",
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        InputTextField(modifier = Modifier
                            .fillMaxWidth(),
                            hint = "To",
                            isEnabled = false) { input ->

                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
                Button(onClick = {

                }, modifier = Modifier
                    .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                    Text(text = "Convert",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center)
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)) {
            Text(text = "MY BALANCES",
                color = Color.DarkGray,
                modifier = Modifier.padding(8.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(balanceState.data) { balance ->
                BalanceListItem(currency = balance.currency, amount = balance.balance)
            }
        }
    }


//
//    Column(modifier = Modifier.fillMaxSize()) {
//
//        Text(text = "CURRENCY EXCHANGE")
//        Spacer(modifier = Modifier.height(24.dp))
//        Row(modifier = Modifier
//            .fillMaxWidth()) {
//            InputTextField(modifier = Modifier.weight(1f), hint = "From") { input ->
//
//            }
//            Text(text = "USD",
//                modifier = Modifier.weight(1f))
//            Icon(painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
//                contentDescription = "dropdown",
//                modifier = Modifier.weight(1f))
//            InputTextField(modifier = Modifier.weight(1f), hint = "From") { input ->
//
//            }
//            Text(text = "PHP",
//                modifier = Modifier.weight(1f))
//            Icon(painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
//                contentDescription = "dropdown",
//                modifier = Modifier.weight(1f))
//        }
//    }

//    Column {
//        rateState.data?.let {
//            Text(text = "${it.result}")
//        }
//
//        Button(onClick = {
//            currencyViewModel.getCurrencyExchangeRate("PHP", "USD", 1.00)
//        }) {
//            Text("Exchange")
//        }
//    }
}