package hr.bornaseatovic.myapplication.main.features.calculation.map

import androidx.compose.animation.*
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    val coroutineScope = rememberCoroutineScope()

    var init by remember {
        mutableStateOf(false)
    }

    var addressButtonPressed by remember {
        mutableStateOf(false)
    }

    var coordinatesButtonPressed by remember {
        mutableStateOf(true)
    }

    var drawPolygonButtonPressed by remember {
        mutableStateOf(false)
    }

    var animateSearch by remember {
        mutableStateOf(false)
    }

    val searchAlpha by animateFloatAsState(
        targetValue = if (animateSearch) 0.5f else 0f,
        tween(200, easing = EaseInOut)
    )

    var animateCoordinates by remember {
        mutableStateOf(false)
    }

    val coordinatesAlpha by animateFloatAsState(
        targetValue = if (!animateCoordinates) 0.5f else 0f,
        tween(200, easing = EaseInOut)
    )

    val coordinatesFieldWidth by animateDpAsState(
        targetValue = if (drawPolygonButtonPressed) 150.dp else if (!animateCoordinates) 130.dp else 210.dp,
        tween(200, easing = EaseInOut)
    )

    val latitudeTextOffset by animateDpAsState(
        targetValue = if (!animateCoordinates) 0.dp else 80.dp,
        tween(200, easing = EaseInOut)
    )

    val closeButtonWidth by animateDpAsState(
        targetValue = if (drawPolygonButtonPressed) 0.dp else 45.dp,
        tween(200, easing = EaseInOut)
    )

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    modifier = Modifier.weight(1f),
                    visible = init,
                    enter = slideInVertically(
                        initialOffsetY = { -4000 },
                        animationSpec = tween(700, 400, easing = EaseInOut)
                    )
                ) {
                    if (coordinatesButtonPressed) {
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(end = 10.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(45.dp)
                                    .width(coordinatesFieldWidth)
                                    .background(MaterialTheme.colors.background),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Latitude",
                                    style = Poppins_Regular,
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .alpha(coordinatesAlpha)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(45.dp)
                                    .width(coordinatesFieldWidth)
                                    .background(MaterialTheme.colors.background),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Longitude",
                                    style = Poppins_Regular,
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .offset(x = latitudeTextOffset)
                                        .padding(start = 20.dp)
                                        .alpha(coordinatesAlpha)
                                )
                            }
                        }
                    }

                    if (addressButtonPressed) {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .height(45.dp)
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
                                    .alpha(searchAlpha)
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
                                    .alpha(searchAlpha)
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = init,
                    enter = slideInVertically(
                        initialOffsetY = { -4000 },
                        animationSpec = tween(700, 400, easing = EaseInOut)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                viewModel.onIntent(MapScreenIntents.GoBack)
                            }
                            .height(45.dp)
                            .width(closeButtonWidth)
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

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Spacer(modifier = Modifier.weight(1f))

                AnimatedVisibility(
                    visible = init,
                    enter = slideInVertically(
                        initialOffsetY = { 500 },
                        animationSpec = tween(400, 600, easing = EaseInOut)
                    )
                ) {
                    Box(modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            drawPolygonButtonPressed = !drawPolygonButtonPressed
                        }
                        .background(Color.Black)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_draw_polygon),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .align(Alignment.Center)

                        )
                    }
                }
            }

            Row(
                modifier = Modifier
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
                                    coroutineScope.launch {
                                        animateCoordinates = true
                                        delay(200)
                                        coordinatesButtonPressed = false
                                        addressButtonPressed = true
                                        animateSearch = true
                                    }
                                }
                                .background(MaterialTheme.colors.background)
                                .height(50.dp)
                                .width(75.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_map_pin),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .align(
                                        Alignment.Center
                                    )
                                    .alpha(if (addressButtonPressed) 1f else 0.4f)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    coroutineScope.launch {
                                        animateSearch = false
                                        delay(200)
                                        addressButtonPressed = false
                                        coordinatesButtonPressed = true
                                        animateCoordinates = false
                                    }
                                }
                                .background(MaterialTheme.colors.background)
                                .height(50.dp)
                                .width(75.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_globe),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .align(
                                        Alignment.Center
                                    )
                                    .alpha(if (coordinatesButtonPressed) 1f else 0.4f)
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = init,
                    enter = slideInVertically(
                        initialOffsetY = { 500 },
                        animationSpec = tween(400, 700, easing = EaseInOut)
                    )
                ) {
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
    }
}