package hr.bornaseatovic.myapplication.main.features.calculation.map

import androidx.compose.animation.*
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import hr.bornaseatovic.myapplication.R
import hr.bornaseatovic.myapplication.ui.theme.Poppins_Regular

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    var init by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = { init = true })

    val myLocation = LatLng(45.8150, 15.9819)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myLocation, 10f)
    }

    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false
            )
        )
    }

    var settingsButtonClicked by remember {
        mutableStateOf(false)
    }

    val gearRotationDegree by animateFloatAsState(
        targetValue = if (settingsButtonClicked) 180f else 0f,
        tween(500, easing = EaseInOut)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .matchParentSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings
        ) {}
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 40.dp)
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                modifier = Modifier.weight(1f),
                visible = init,
                enter = slideInHorizontally(
                    initialOffsetX = { 1500 },
                    animationSpec = tween(400, 500, easing = EaseInOut)
                )
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .height(60.dp)
                        .weight(1f)
                        .background(MaterialTheme.colors.background),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .offset(y = 4.dp)
                            .size(28.dp)
                    )
                    Text(
                        text = "Search",
                        style = Poppins_Regular,
                        fontSize = 13.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .alpha(0.5f)
                    )
                }
            }

            AnimatedVisibility(
                visible = init,
                enter = slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(400, 650, easing = EaseInOut)
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            viewModel.onIntent(MapScreenIntents.GoBack)
                        }
                        .size(60.dp)
                        .background(Color.Black)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))

            AnimatedVisibility(
                visible = settingsButtonClicked,
                enter = expandHorizontally(
                    initialWidth = { 0 },
                    animationSpec = tween(500)
                ),
                exit = shrinkHorizontally(
                    targetWidth = { 0 },
                    animationSpec = tween(500)
                )

            ) {
                Row(modifier = Modifier.wrapContentSize()) {
                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                settingsButtonClicked = false
                            }
                            .background(MaterialTheme.colors.background)
                            .height(50.dp)
                            .width(75.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_map_pin),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                settingsButtonClicked = false
                            }
                            .background(MaterialTheme.colors.background)
                            .height(50.dp)
                            .width(75.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_globe),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                }
            }

            Box(modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    settingsButtonClicked = !settingsButtonClicked
                }
                .background(Color.Black)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gear),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .rotate(gearRotationDegree)
                )
            }

        }
    }
}