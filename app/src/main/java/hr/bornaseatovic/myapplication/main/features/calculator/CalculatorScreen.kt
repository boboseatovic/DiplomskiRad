package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
       Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Spremi podatke", modifier = Modifier
                .clickable {
                    viewModel.onIntent(CalculatorIntents.PressAButton)
                })
           Text(text = viewState.tekst)
        }
    }
}