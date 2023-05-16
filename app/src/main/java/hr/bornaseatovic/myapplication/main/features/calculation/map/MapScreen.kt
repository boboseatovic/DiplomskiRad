package hr.bornaseatovic.myapplication.main.features.calculation.map

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import hr.bornaseatovic.myapplication.R
import hr.bornaseatovic.myapplication.ui.states.CalculatorFieldState
import hr.bornaseatovic.myapplication.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value

    val coroutineScope = rememberCoroutineScope()

    var init by remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusLocalManager = LocalFocusManager.current

    val focusRequester = remember {
        FocusRequester()
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

    var searchPressed by remember {
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
        targetValue = if (searchPressed) 150.dp else if (!animateCoordinates) 130.dp else 210.dp,
        tween(200, easing = EaseInOut)
    )

    val longitudeTextOffset by animateDpAsState(
        targetValue = if (!animateCoordinates) 0.dp else 40.dp,
        tween(200, easing = EaseInOut)
    )

    val closeButtonWidth by animateDpAsState(
        targetValue = if (searchPressed) 0.dp else 45.dp,
        tween(200, easing = EaseInOut)
    )

    LaunchedEffect(key1 = Unit, block = { init = true })


    var latitudePlaceholderVisible by remember {
        mutableStateOf(true)
    }

    var longitudePlaceholderVisible by remember {
        mutableStateOf(true)
    }
    var myLocation by remember {
        mutableStateOf(
            LatLng(
                45.8150,
                15.9819
            )
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myLocation, 13f)
    }


    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false
            )
        )
    }

    var properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.SATELLITE
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
            .background(Color.Black)
    ) {
        GoogleMap(
            modifier = Modifier
                .matchParentSize()
                .alpha(if (drawPolygonButtonPressed) 0.5f else 1f),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            properties = properties
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
                Spacer(modifier = Modifier.weight(1f))
                AnimatedVisibility(
                    modifier = Modifier.weight(100f),
                    visible = init && !drawPolygonButtonPressed,
                    enter = slideInVertically(
                        initialOffsetY = { -4000 },
                        animationSpec = tween(700, if (init) 400 else 0, easing = EaseInOut)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { -4000 },
                        animationSpec = tween(700, easing = EaseInOut)
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
                                    .background(MaterialTheme.colors.background)
                                    .padding(horizontal = 15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BasicTextField(
                                    modifier = Modifier
                                        .weight(1f)
                                        .onFocusChanged {
                                            if (it.isFocused) {
                                                latitudePlaceholderVisible = false
                                                searchPressed = true
                                            }
                                        },
                                    value = viewState.latitudeValue,
                                    singleLine = true,
                                    onValueChange = {
                                        viewModel.onIntent(MapScreenIntents.InputLatitude(it))
                                    },
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(onDone = {
                                        keyboardController?.hide()
                                        focusLocalManager.clearFocus(true)
                                        searchPressed = false
                                        latitudePlaceholderVisible = true
                                        focusRequester.requestFocus()
                                    }),
                                    textStyle = Poppins_Regular_11.copy(
                                        color = Color.Black,
                                    ),
                                    maxLines = 1
                                ) { innerPaddingValue ->
                                    if (viewState.latitudeValue.isBlank() && latitudePlaceholderVisible) {
                                        Text(
                                            text = "Latitude",
                                            style = Poppins_Regular,
                                            fontSize = 11.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .alpha(if (viewState.latitudeValue.isBlank()) coordinatesAlpha else 1f)
                                        )
                                    }
                                    innerPaddingValue()
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(45.dp)
                                    .width(coordinatesFieldWidth)
                                    .background(MaterialTheme.colors.background)
                                    .padding(horizontal = 15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BasicTextField(
                                    modifier = Modifier
                                        .weight(1f)
                                        .offset(x = longitudeTextOffset)
                                        .focusRequester(focusRequester)
                                        .onFocusChanged {
                                            if (it.isFocused) {
                                                longitudePlaceholderVisible = false
                                                searchPressed = true
                                            }
                                        },
                                    value = viewState.longitudeValue,
                                    singleLine = true,
                                    onValueChange = {
                                        viewModel.onIntent(MapScreenIntents.InputLongitude(it))
                                    },
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(onDone = {
                                        keyboardController?.hide()
                                        focusLocalManager.clearFocus(true)
                                        searchPressed = false
                                        longitudePlaceholderVisible = true
                                        if (viewState.latitudeValue.isNotBlank() && viewState.longitudeValue.isNotBlank()) {
                                            myLocation = LatLng(
                                                viewState.latitudeValue.toDouble(),
                                                viewState.longitudeValue.toDouble()
                                            )
                                        }
                                        coroutineScope.launch {
                                            cameraPositionState.animate(
                                                CameraUpdateFactory.newLatLng(
                                                    LatLng(
                                                        viewState.latitudeValue.toDouble(),
                                                        viewState.longitudeValue.toDouble()
                                                    )
                                                ), 1000
                                            )
                                        }
                                    }),
                                    textStyle = Poppins_Regular_11.copy(
                                        color = Color.Black,
                                    ),
                                    maxLines = 1
                                ) { innerPaddingValue ->
                                    if (viewState.longitudeValue.isBlank() && longitudePlaceholderVisible) {
                                        Text(
                                            text = "Longitude",
                                            style = Poppins_Regular,
                                            fontSize = 11.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .offset(x = longitudeTextOffset)
                                                .alpha(if (viewState.longitudeValue.isBlank()) coordinatesAlpha else 1f)
                                        )
                                    }
                                    innerPaddingValue()
                                }
                            }
                        }
                    }

                    if (addressButtonPressed) {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    searchPressed = !searchPressed
                                }
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
                                fontSize = 11.sp,
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
                                if (drawPolygonButtonPressed) {
                                    drawPolygonButtonPressed = false
                                } else {
                                    viewModel.onIntent(MapScreenIntents.GoBack)
                                }
                            }
                            .height(45.dp)
                            .width(closeButtonWidth)
                            .background(Color.Black)
                    ) {
                        Icon(
                            painter = painterResource(id = if (drawPolygonButtonPressed) R.drawable.ic_check else R.drawable.ic_close),
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
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
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
                    visible = init && !drawPolygonButtonPressed,
                    enter = slideInVertically(
                        initialOffsetY = { 500 },
                        animationSpec = tween(400, 700, easing = EaseInOut)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { 500 },
                        animationSpec = tween(400, easing = EaseInOut)
                    )
                ) {
                    Box(modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            settingsButtonClicked = !settingsButtonClicked
                        }
                        .background(White1)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_gear),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .rotate(gearRotationDegree)
                        )
                    }
                }

                AnimatedVisibility(
                    visible = init && !drawPolygonButtonPressed,
                    enter = slideInVertically(
                        initialOffsetY = { 500 },
                        animationSpec = tween(400, 600, easing = EaseInOut)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { 500 },
                        animationSpec = tween(400, easing = EaseInOut)
                    )
                ) {
                    Box(modifier = Modifier
                        .padding(start = 10.dp)
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            drawPolygonButtonPressed = true
                            settingsButtonClicked = false
                        }
                        .background(Yellow1)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_draw_polygon),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .align(Alignment.Center)

                        )
                    }
                }

            }
        }
    }
}