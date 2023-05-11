package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.bornaseatovic.myapplication.ui.components.CalculatorField
import hr.bornaseatovic.myapplication.ui.components.PrimaryButton
import hr.bornaseatovic.myapplication.ui.theme.OffBlack

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    HorizontalPager(modifier = Modifier.fillMaxSize(), pageCount = 3) { page ->
        when(page) {
           0 -> FirstScreen(viewModel = viewModel, viewState = viewState)
           1 -> SecondScreen(viewModel = viewModel, viewState = viewState)
           2 -> ThirdScreen(viewModel = viewModel, viewState = viewState)
        }

    }
}