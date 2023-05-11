package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.bornaseatovic.myapplication.ui.components.CalculatorField
import hr.bornaseatovic.myapplication.ui.components.PrimaryButton
import hr.bornaseatovic.myapplication.ui.theme.OffBlack

@Composable
fun FirstScreen(
    viewModel: CalculatorViewModel,
    viewState: CalculatorViewState
) {
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

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Nagib istočnog krova",
                value = viewState.nagibIstocnogKrova,
                unit = "º"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniNagibIstocnogKrova(it))
            }

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Površina južnog krova",
                value = viewState.povrsinaJunznogKrova,
                unit = "m2"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniPovrsinaJuznogKrova(it))
            }

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Nagib južnog krova",
                value = viewState.nagibJunznogKrova,
                unit = "º"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniNagibJuznogKrova(it))
            }

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Površina zapadnog krova",
                value = viewState.povrsinaZapadnogKrova,
                unit = "m2"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniPovrsinaZapadnogKrova(it))
            }

            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Nagib zapadnog krova",
                value = viewState.nagibZapadnogKrova,
                unit = "º"
            ) {
                viewModel.onIntent(CalculatorIntents.PromjeniNagibZapadnogKrova(it))
            }

            Text(text = viewState.text, color = OffBlack)
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text = "Dalje") {
            viewModel.onIntent(CalculatorIntents.PressAButton)
        }
    }
}