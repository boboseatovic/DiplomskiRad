package hr.bornaseatovic.myapplication.main.features.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import hr.bornaseatovic.myapplication.ui.theme.*
import hr.bornaseatovic.myapplication.R
import hr.bornaseatovic.myapplication.main.features.calculation.map.MapScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    var init by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        init = true
    })


    val infiniteTransition = rememberInfiniteTransition()
    val iconOffset by infiniteTransition.animateFloat(
        initialValue = 3f,
        targetValue = -3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        )
    )
    val iconAlpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (viewState.textAnimation.value) 0f else 1f,
        tween(3000, easing = EaseInOut)
    )

    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 60.dp)
                .wrapContentSize()
        ) {
            AnimatedVisibility(
                visible = init,
                enter = slideInHorizontally(
                    initialOffsetX = { -100 },
                    animationSpec = tween(500, easing = EaseInOut)
                ) + fadeIn(tween(500)),
                exit = fadeOut(
                    tween(300)
                )
            ) {
                Text(
                    text = "Welcome",
                    style = ZillaSlab_SemiBold,
                    fontSize = 48.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .alpha(textAlpha)
                )
            }

            AnimatedVisibility(
                visible = init,
                enter = slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(500, 100, easing = EaseInOut)
                ) + fadeIn(tween(500)),
                exit = fadeOut(
                    tween(300)
                )
            ) {
                Text(
                    text = "back!",
                    style = Montserrat_Light,
                    fontSize = 40.sp,
                    color = Color.Black,
                    modifier = Modifier.alpha(textAlpha)
                )
            }
        }
        
        Text(text = viewState.text, color = Color.Black)

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(
            visible = init,
            enter = slideInVertically(
                initialOffsetY = { 1000 },
                animationSpec = tween(400, 500, easing = EaseInOut)
            ) + fadeIn(tween(500, easing = EaseInOut)),
            exit = slideOutVertically(
                targetOffsetY = { 1000 },
                animationSpec = tween(400, easing = EaseInOut)
            ) + fadeOut(tween(500, easing = EaseInOut))
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        init = false
                        viewModel.onIntent(HomeScreenIntents.OpenMap)
                    }
                    .background(Yellow1)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New calculation",
                    style = ZillaSlab_SemiBold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .alpha(textAlpha)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .alpha(iconAlpha)
                        .offset(y = iconOffset.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_up_chevron),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.align(Alignment.Center)

                    )
                }
            }
        }
    }
}