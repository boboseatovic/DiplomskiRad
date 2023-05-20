package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import hr.bornaseatovic.myapplication.ui.theme.OffBlack
import hr.bornaseatovic.myapplication.ui.theme.Yellow1
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThirdScreen(
    viewModel: CalculatorViewModel,
    viewState: CalculatorViewState,
) {

//    val barChartInputsPercent = viewState.monthlyProduction.map { it/maxMonthlyProduction }

    val defaultMaxHeight = 200.dp

    val borderColor = OffBlack
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
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
                }
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            viewState.monthlyProduction?.let {
                it.forEachIndexed { index, item ->
                Bar(
                    value = item / it.max(),
                    color = if (index % 2 == 0) OffBlack else Yellow1,
                    maxHeight = defaultMaxHeight
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