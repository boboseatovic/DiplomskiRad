package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.bornaseatovic.myapplication.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThirdScreen(
    viewModel: CalculatorViewModel,
    viewState: CalculatorViewState,
) {

//    val barChartInputsPercent = viewState.monthlyProduction.map { it/maxMonthlyProduction }
    val list: MutableList<Double> = mutableListOf()

    viewState.monthlyProduction?.forEachIndexed { index, production ->
        list.add(viewState.monthlyConsumption[index])
        list.add(production)
    }

    val defaultMaxHeight = 200.dp

    val borderColor = OffBlack
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(defaultMaxHeight)
                .drawBehind {
                    // draw X-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                    // draw Y-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = strokeWidth
                    )
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            list.forEachIndexed { index, item ->
                Bar(
                    value = item / list.max(),
                    color = if (index % 2 == 0) OffBlack else Yellow,
                    maxHeight = defaultMaxHeight
                )

            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Consumption",
                        style = Poppins_Regular,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "${viewState.completeConsumption} kWh",
                        style = Poppins_Regular,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Production",
                        style = Poppins_Regular,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "${viewState.yearlyProduction} kWh",
                        style = Poppins_Regular,
                        fontSize = 20.sp,
                        color = Yellow
                    )
                }
            }
        }

    }
}

@Composable
private fun RowScope.Bar(
    value: Double,
    color: Color,
    maxHeight: Dp
) {

    val itemHeight = remember(value) { value * maxHeight.value }

    Spacer(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .height(itemHeight.dp)
            .weight(1f)
            .background(color)
    )

}