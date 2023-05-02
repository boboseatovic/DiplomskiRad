package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.bornaseatovic.myapplication.ui.components.CalculatorField
import hr.bornaseatovic.myapplication.ui.components.PrimaryButton
import hr.bornaseatovic.myapplication.ui.theme.OffBlack

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Geografska širina",
                value = viewState.geoSirina,
                unit = "º"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniGeoSirina(it))
            }

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Geografska dužina",
                value = viewState.geoDuzina,
                unit = "º"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniGeoDuzina(it))
            }

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Površina istočnog krova",
                value = viewState.povrsinaIstocnogKrova,
                unit = "m2"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniPovrsinaIstocnogKrova(it))
            }

            Text(text = viewState.text, color = OffBlack)
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text = "Izračunaj") {
            viewModel.onIntent(CalculatorIntents.PressAButton)
        }
    }
}