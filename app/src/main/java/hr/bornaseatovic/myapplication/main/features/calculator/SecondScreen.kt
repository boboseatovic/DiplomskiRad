package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.bornaseatovic.myapplication.ui.components.CalculatorField
import hr.bornaseatovic.myapplication.ui.components.PrimaryButton

@Composable
fun SecondScreen(
    viewModel: CalculatorViewModel,
    viewState: CalculatorViewState,
    onDalje: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            viewState.mjesecnaPotrosnja.forEachIndexed { index, potrosnja ->
//                CalculatorField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight(),
//                    label = "Prosječna potrošnja u ${index + 1}. mjesecu",
//                    value = potrosnja
//                ) {
//                    viewModel.onIntent(CalculatorIntents.PromjeniMjesecnaPotrosnja(index, it))
//                }
//            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 1.mjesecu",
                value = viewState.potrosnja1mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni1Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 2.mjesecu",
                value = viewState.potrosnja2mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni2Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 3.mjesecu",
                value = viewState.potrosnja3mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni3Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 4.mjesecu",
                value = viewState.potrosnja4mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni4Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 5.mjesecu",
                value = viewState.potrosnja5mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni5Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 6.mjesecu",
                value = viewState.potrosnja6mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni6Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 7.mjesecu",
                value = viewState.potrosnja7mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni7Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 8.mjesecu",
                value = viewState.potrosnja8mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni8Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 9.mjesecu",
                value = viewState.potrosnja9mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni9Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 10.mjesecu",
                value = viewState.potrosnja10mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni10Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 11.mjesecu",
                value = viewState.potrosnja11mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni11Mjesec(it))
            }
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u 12.mjesecu",
                value = viewState.potrosnja12mjesec,
            ) {
                viewModel.onIntent(CalculatorIntents.Promjeni12Mjesec(it))
            }
        }

        PrimaryButton(text = "Dalje") {
            onDalje()
        }
    }
}