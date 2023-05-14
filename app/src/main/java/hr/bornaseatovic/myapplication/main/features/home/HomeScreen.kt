package hr.bornaseatovic.myapplication.main.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.bornaseatovic.myapplication.ui.theme.*
import hr.bornaseatovic.myapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    var init by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        init = true
    })

    val coroutineScope = rememberCoroutineScope()

    val swipeableState = rememberSwipeableState(false, tween(500))

    val infiniteTrasition = rememberInfiniteTransition()
    val chevronPositionLoop by infiniteTrasition.animateFloat(
        initialValue = 3f,
        targetValue = -3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        )
    )
    val chevronAlphaLoop by infiniteTrasition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        )
    )
    var buttonClicked by remember {
        mutableStateOf(false)
    }
    var textAnimation by remember {
        mutableStateOf(false)
    }
    val buttonHeight by animateDpAsState(
        targetValue = if (buttonClicked) LocalConfiguration.current.screenHeightDp.dp + 40.dp else 80.dp,
        tween(600, easing = EaseInOut)
    )
    val buttonWidth by animateDpAsState(
        targetValue = if (buttonClicked) LocalConfiguration.current.screenWidthDp.dp else LocalConfiguration.current.screenWidthDp.dp - 24.dp,
        tween(600, easing = EaseInOut)
    )
    val screenPadding by animateDpAsState(
        targetValue = if (buttonClicked) 0.dp else 24.dp,
        tween(600, easing = EaseInOut)
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (textAnimation) 0f else 1f,
        tween(400, easing = EaseInOut)
    )
    val textOffset by animateDpAsState(
        targetValue = if (textAnimation) 60.dp else 0.dp,
        tween(400, easing = EaseInOut)
    )

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(vertical = screenPadding)
            .padding(start = screenPadding)
    ) {
        AnimatedVisibility(
            visible = init,
            enter = slideInHorizontally(
                initialOffsetX = { -100 },
                animationSpec = tween(500, easing = EaseInOut)
            ) + fadeIn(tween(500))
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .wrapContentSize()
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "Welcome",
                    style = ZillaSlab_SemiBold,
                    fontSize = 48.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 3.dp)
                        .alpha(textAlpha)
                )
                Text(
                    text = "back",
                    style = Montserrat_Light,
                    fontSize = 40.sp,
                    color = Color.Black,
                    modifier = Modifier.alpha(textAlpha)
                )
            }
        }


        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = init,
            enter = slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500, easing = EaseInOut)
            ) + fadeIn(tween(500, easing = EaseInOut))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        onClick = {
                            coroutineScope.launch {
                                textAnimation = true
                                delay(200)
                                buttonClicked = true
                                viewModel.onIntent(HomeScreenIntents.PressCalculateNew)
                                delay(600)
                                textAnimation = false
                                buttonClicked = false
                            }
                        },
                        indication = null
                    )
                    .background(Color.Black)
                    .width(buttonWidth)
                    .height(buttonHeight)
                    .padding(24.dp)
            ) {
                Text(
                    text = "New calculation",
                    style = ZillaSlab,
                    color = White1,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = textOffset)
                        .alpha(textAlpha)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_up_chevron),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(end = 40.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = textOffset/*, y = chevronPositionLoop.dp*/)
//                        .alpha(chevronAlphaLoop)
                        .alpha(textAlpha)
                )
            }
        }
    }
}