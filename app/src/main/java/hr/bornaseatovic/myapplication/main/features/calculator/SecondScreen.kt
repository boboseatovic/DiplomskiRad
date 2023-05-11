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
    viewState: CalculatorViewState
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for(i in 1..12) {
            CalculatorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = "Energetska potrošnja u $i.mjesecu"
            )
        }

        PrimaryButton(text = "Dalje") {

        }
    }
}