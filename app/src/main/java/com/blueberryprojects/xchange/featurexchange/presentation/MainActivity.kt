package com.blueberryprojects.xchange.featurexchange.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.blueberryprojects.xchange.common.components.MainScreen
import com.blueberryprojects.xchange.featurexchange.presentation.viewmodel.CurrencyViewModel
import com.blueberryprojects.xchange.ui.theme.XChangeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val currencyViewModel: CurrencyViewModel = hiltViewModel()
//            val rateState = currencyViewModel.rateState.value
//
////            val balanceView: BalanceViewModel = hiltViewModel()
////
////            val balances by remember {
////                mutableStateOf(balanceView.balances)
////            }
//
//            currencyViewModel.getCurrencyExchangeRate("USD", "PHP", 1.00)

            XChangeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    rateState.data?.let {
//                        Text(text = "${it.result}")
//                    }

                    MainScreen()
                }
            }
        }
    }
}