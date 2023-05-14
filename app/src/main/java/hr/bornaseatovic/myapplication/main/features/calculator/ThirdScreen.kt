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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThirdScreen(
    viewModel: CalculatorViewModel,
    viewState: CalculatorViewState,
    navigate: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    var clicked by remember {
        mutableStateOf(false)
    }

    val boxHeight by animateDpAsState(
        targetValue = if (clicked) LocalConfiguration.current.screenHeightDp.dp else 60.dp,
        tween(300)
    )
    val boxWidth by animateDpAsState(
        targetValue = if (clicked) LocalConfiguration.current.screenWidthDp.dp else LocalConfiguration.current.screenWidthDp.dp - 40.dp,
        tween(300)
    )

    val boxOffset by animateDpAsState(targetValue = if (clicked) 0.dp else -20.dp, tween(300))


    Box(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding()) {

        Box(
            modifier = Modifier
                .offset(y = boxOffset)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .width(boxWidth)
                .height(boxHeight)
                .align(Alignment.BottomCenter)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        clicked = !clicked
                    },
                    indication = null
                )
        ) {
            if (clicked) {

            }
        }
    }
}