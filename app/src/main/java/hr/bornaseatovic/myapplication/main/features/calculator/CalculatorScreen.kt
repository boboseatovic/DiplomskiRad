package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.bornaseatovic.myapplication.ui.components.CalculatorField
import hr.bornaseatovic.myapplication.ui.components.PrimaryButton
import hr.bornaseatovic.myapplication.ui.theme.OffBlack
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel(),
    navigate: () -> Unit
) {
    val viewState = viewModel.viewState.collectAsState().value

    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState()

    HorizontalPager(
        modifier = Modifier.padding(top = 30.dp).navigationBarsPadding().fillMaxSize(),
        pageCount = 3,
        state = pagerState
    ) { page ->
        when (page) {
            0 -> FirstScreen(viewModel = viewModel, viewState = viewState) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
            1 -> SecondScreen(viewModel = viewModel, viewState = viewState) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
            2 -> ThirdScreen(viewModel = viewModel, viewState = viewState)
        }

    }
}