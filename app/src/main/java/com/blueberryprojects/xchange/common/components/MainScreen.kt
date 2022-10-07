package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.blueberryprojects.xchange.common.extensions.formatAmount
import com.blueberryprojects.xchange.featurexchange.domain.model.Balance
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.BalanceViewModel
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.CurrencyViewModel

@Composable
fun MainScreen(
    currencyViewModel: CurrencyViewModel = hiltViewModel(),
    balanceViewModel: BalanceViewModel = hiltViewModel(),
) {
    val rateState = currencyViewModel.rateState.value
    val balanceState = balanceViewModel.balanceState.value

    var messageDialog by remember {
        mutableStateOf("")
    }

    var titleDialog by remember {
        mutableStateOf("Oops!")
    }

    var isFromSelected by remember {
        mutableStateOf(false)
    }

    var fromSelectedCurrency by remember {
        mutableStateOf("Select")
    }

    var fromInputAmount by remember {
        mutableStateOf("")
    }

    var toAmount by remember {
        mutableStateOf("")
    }

    var currentMaxBalance by remember {
        mutableStateOf(0.00)
    }

    var toSelectedCurrency by remember {
        mutableStateOf("Select")
    }

    var isToSelected by remember {
        mutableStateOf(false)
    }

    if (messageDialog.isNotBlank()) {
        MessageDialog(titleDialog, messageDialog) {
            messageDialog = ""
        }
    }

    val userCurrencies = balanceState.data
    val allCurrencies = stringArrayResource(id = R.array.array_currency_codes).map {
        Balance().apply {
            currency = it
            balance = 0.00
        }
    }

    if (isFromSelected) {
        CurrencyDialog(currencies = userCurrencies, onCurrencyClick = {
            isFromSelected = false
            fromSelectedCurrency = it.currency
            fromInputAmount = ""
            toAmount = ""
            currentMaxBalance = it.balance
        }) {
            isFromSelected = false
        }
    }

    if (isToSelected) {
        CurrencyDialog(currencies = allCurrencies.toList(), onCurrencyClick = {
            isToSelected = false
            toSelectedCurrency = it.currency
        }) {
            isToSelected = false
        }
    }

    if (fromInputAmount.isNotBlank() && fromInputAmount.toDouble() > 0.00) {
        LaunchedEffect(key1 = fromInputAmount) {
            currencyViewModel.getCurrencyExchangeRate(fromSelectedCurrency, toSelectedCurrency, fromInputAmount.toDouble())
        }

        LaunchedEffect(key1 = toSelectedCurrency) {
            currencyViewModel.getCurrencyExchangeRate(fromSelectedCurrency, toSelectedCurrency, fromInputAmount.toDouble())
        }
    }

    rateState.data?.let {
        toAmount = it.result.formatAmount()
    }

    if (fromInputAmount == "") {
        toAmount = ""
        currencyViewModel.stopJob()
    }

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
                        Text(text = fromSelectedCurrency,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                isFromSelected = true
                            })
                        Spacer(modifier = Modifier.height(8.dp))
                        InputTextField(modifier = Modifier
                            .clickable {
                                if (fromSelectedCurrency == "Select" || toSelectedCurrency == "Select") {
                                    titleDialog = "Oops!"
                                    messageDialog = "Please select currencies first."
                                }
                            }
                            .fillMaxWidth(),
                            hint = "From",
                            text = fromInputAmount,
                            maxValue = currentMaxBalance,
                            isEnabled = fromSelectedCurrency != "Select" && toSelectedCurrency != "Select") { input ->
                            fromInputAmount = input
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
                        Text(text = toSelectedCurrency,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                isToSelected = true
                            })
                        Spacer(modifier = Modifier.height(8.dp))
                        InputTextField(modifier = Modifier
                            .fillMaxWidth(),
                            hint = "To",
                            text = toAmount,
                            isEnabled = false) {}
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
                Button(onClick = {
                    if (toSelectedCurrency.isNotBlank()
                        && toAmount.isNotBlank() && toAmount.toDouble() > 0.00
                    ) {
                        val inputAmount = fromInputAmount.toDouble()
                        val commissionFee = balanceViewModel.getCommissionFee(inputAmount, fromSelectedCurrency)
                        val balanceAfterFee = balanceViewModel.getBalanceAfterFee(inputAmount, commissionFee)

                        if (inputAmount + commissionFee > currentMaxBalance) {
                            titleDialog = "Oops!"
                            messageDialog = "Insufficient funds after fee."
                        } else {
                            val fromBalance = Balance().apply {
                                currency = fromSelectedCurrency
                                balance = balanceAfterFee
                            }

                            val toBalance = Balance().apply {
                                currency = toSelectedCurrency
                                balance = toAmount.toDouble()
                            }

                            balanceViewModel.insertBalance(fromBalance, toBalance)

                            val message =
                                "You have converted ${balanceAfterFee.formatAmount()} $fromSelectedCurrency to $toAmount $toSelectedCurrency. Commission Fee - ${commissionFee.formatAmount()} $fromSelectedCurrency."

                            titleDialog = "Currency converted"
                            messageDialog = message

                            fromInputAmount = ""
                            toAmount = ""
                            fromSelectedCurrency = "Select"
                            toSelectedCurrency = "Select"
                        }
                    } else {
                        titleDialog = "Oops!"
                        messageDialog = "Please select the amount first."
                    }
                }, modifier = Modifier
                    .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                    Text(text = "Exchange",
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
                fontSize = 12.sp,
                modifier = Modifier.padding(6.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(balanceState.data) { balance ->
                BalanceListItem(currency = balance.currency, amount = balance.balance)
            }
        }
    }
}