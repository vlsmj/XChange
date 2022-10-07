package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.xchange.R
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.CurrencyViewModel

@Composable
fun MainScreen(
    currencyViewModel: CurrencyViewModel = hiltViewModel(),
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.dark_navy_blue))
            .padding(16.dp),
            contentAlignment = Alignment.Center) {
            Column {
                Text(text = "CURRENCY EXCHANGE",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(24.dp))
                Row(Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(16.dp)) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)) {
                        Text(text = "EUR",
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "1000.00",
                            fontWeight = FontWeight.SemiBold)
                    }
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_exchange),
                            contentDescription = "exchange_icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)) {
                        Text(text = "USD",
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "1000.00",
                            fontWeight = FontWeight.SemiBold)
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
    }


//    val rateState = currencyViewModel.rateState.value
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