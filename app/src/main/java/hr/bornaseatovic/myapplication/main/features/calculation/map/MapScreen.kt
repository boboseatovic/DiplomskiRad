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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import hr.bornaseatovic.myapplication.R
import hr.bornaseatovic.myapplication.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusLocalManager = LocalFocusManager.current

    var init by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewState.location.value, 17.5f)
    }

    LaunchedEffect(key1 = Unit, block = { init = true })
    LaunchedEffect(viewState.location) {
        cameraPositionState.animate(
            CameraUpdateFactory.newLatLng(viewState.location.value),
            500)
        cameraPositionState.animate(
            CameraUpdateFactory.zoomTo(17.5f)
        )
    }

    val searchAlpha by animateFloatAsState(
        targetValue = if (viewState.animateSearch.value) 0.5f else 0f,
        tween(200, easing = EaseInOut)
    )
    val coordinatesAlpha by animateFloatAsState(
        targetValue = if (!viewState.animateCoordinates.value) 0.5f else 0f,
        tween(200, easing = EaseInOut)
    )
    val coordinatesFieldWidth by animateDpAsState(
        targetValue = if (viewState.searchPressed.value) 150.dp else
            if (!viewState.animateCoordinates.value) 130.dp else 210.dp,
        tween(200, easing = EaseInOut)
    )
    val longitudeTextOffset by animateDpAsState(
        targetValue = if (!viewState.animateCoordinates.value) 0.dp else 80.dp,
        tween(200, easing = EaseInOut)
    )
    val closeButtonWidth by animateDpAsState(
        targetValue = if (viewState.searchPressed.value) 0.dp else 45.dp,
        tween(200, easing = EaseInOut)
    )
    val gearRotationDegree by animateFloatAsState(
        targetValue = if (viewState.settingsButtonPressed.value) 180f else 0f,
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
                .alpha(if (viewState.drawPolygonButtonPressed.value) 0.5f else 1f),
            cameraPositionState = cameraPositionState,
            uiSettings = viewState.mapUiSettings.value,
            properties = viewState.mapProperties.value
        ) {

        }
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
                    visible = init && !viewState.drawPolygonButtonPressed.value,
                    enter = slideInVertically(
                        initialOffsetY = { -4000 },
                        animationSpec = tween(700, if (init) 400 else 0, easing = EaseInOut)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { -4000 },
                        animationSpec = tween(700, easing = EaseInOut)
                    )
                ) {
                    if (viewState.coordinatesButtonPressed.value) {
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
                                                viewState.latitudePlaceholderVisible.value = false
                                                viewState.searchPressed.value = true
                                            }
                                        },
                                    value = viewState.latitudeValue,
                                    singleLine = true,
                                    onValueChange = {
                                        viewModel.onIntent(MapScreenIntents.InputLatitude(it))
                                    },
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                                    keyboardActions = KeyboardActions(onNext = {
                                        keyboardController?.hide()
                                        focusLocalManager.clearFocus(true)
                                        viewState.searchPressed.value = false
                                        viewState.latitudePlaceholderVisible.value = true
                                        focusRequester.requestFocus()
                                    }),
                                    textStyle = Poppins_Regular_11.copy(
                                        color = Color.Black,
                                    ),
                                    maxLines = 1
                                ) { innerPaddingValue ->
                                    if (viewState.latitudeValue.isBlank() && viewState.latitudePlaceholderVisible.value) {
                                        Text(
                                            text = "Latitude",
                                            style = Poppins_Regular,
                                            fontSize = 11.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .alpha(1f)
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
                                                viewState.longitudePlaceholderVisible.value = false
                                                viewState.searchPressed.value = true
                                            }
                                        },
                                    value = viewState.longitudeValue,
                                    singleLine = true,
                                    onValueChange = {
                                        viewModel.onIntent(MapScreenIntents.InputLongitude(it))
                                    },
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                    keyboardActions = KeyboardActions(onSearch = {
                                        keyboardController?.hide()
                                        focusLocalManager.clearFocus(true)
                                        viewState.searchPressed.value = false
                                        viewState.longitudePlaceholderVisible.value = true
                                        viewModel.onIntent(MapScreenIntents.SearchLatLong)
                                    }),
                                    textStyle = Poppins_Regular_11.copy(
                                        color = Color.Black,
                                    ),
                                    maxLines = 1
                                ) { innerPaddingValue ->
                                    if (viewState.longitudeValue.isBlank() && viewState.longitudePlaceholderVisible.value) {
                                        Text(
                                            text = "Longitude",
                                            style = Poppins_Regular,
                                            fontSize = 11.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .alpha(if (viewState.longitudeValue.isBlank()) coordinatesAlpha else 1f)
                                        )
                                    }
                                    innerPaddingValue()
                                }
                            }
                        }
                    }

                    if (viewState.addressButtonPressed.value) {
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
                                    .padding(start = 15.dp, end = 10.dp)
                                    .alpha(searchAlpha)
                                    .offset(y = 4.dp)
                                    .size(28.dp)
                            )
                            BasicTextField(
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .weight(1f)
                                    .onFocusChanged {
                                        if (it.isFocused) {
                                            viewState.searchPlaceholderVisible.value = false
                                            viewState.searchPressed.value = true
                                        }
                                    },
                                value = viewState.searchValue,
                                singleLine = true,
                                onValueChange = {
                                    viewModel.onIntent(MapScreenIntents.ChangeSearchValue(it))
                                },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                keyboardActions = KeyboardActions(onSearch = {
                                    keyboardController?.hide()
                                    focusLocalManager.clearFocus(true)
                                    viewState.searchPressed.value = false
                                    viewState.searchPlaceholderVisible.value = true
                                    viewModel.onIntent(MapScreenIntents.SearchAddress)
                                }),
                                textStyle = Poppins_Regular_11.copy(
                                    color = Color.Black,
                                ),
                                maxLines = 1
                            ) { innerPaddingValue ->
                                if (viewState.searchValue.isBlank() && viewState.searchPlaceholderVisible.value) {
                                    Text(
                                        text = "Search",
                                        style = Poppins_Regular,
                                        fontSize = 11.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .alpha(if (viewState.searchValue.isBlank()) searchAlpha else 1f)
                                    )
                                }
                                innerPaddingValue()
                            }
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
                                if (viewState.drawPolygonButtonPressed.value) {
                                    viewModel.onIntent(MapScreenIntents.DoneDrawingPolygon)
                                    viewState.drawPolygonButtonPressed.value = false
                                } else {
                                    viewModel.onIntent(MapScreenIntents.GoBack)
                                }
                            }
                            .height(45.dp)
                            .width(closeButtonWidth)
                            .background(Color.Black)
                    ) {
                        Icon(
                            painter = painterResource(id = if (viewState.drawPolygonButtonPressed.value) R.drawable.ic_check else R.drawable.ic_close),
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
                    visible = viewState.settingsButtonPressed.value,
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
                                        viewState.settingsButtonPressed.value = false
                                        viewState.animateCoordinates.value = true
                                        delay(200)
                                        viewState.coordinatesButtonPressed.value = false
                                        viewState.addressButtonPressed.value = true
                                        viewState.animateSearch.value = true
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
                                    .alpha(if (viewState.addressButtonPressed.value) 1f else 0.4f)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    coroutineScope.launch {
                                        viewState.settingsButtonPressed.value = false
                                        viewState.animateSearch.value = false
                                        delay(200)
                                        viewState.addressButtonPressed.value = false
                                        viewState.coordinatesButtonPressed.value = true
                                        viewState.animateCoordinates.value = false
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
                                    .alpha(if (viewState.coordinatesButtonPressed.value) 1f else 0.4f)
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = init && !viewState.drawPolygonButtonPressed.value,
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
                            viewState.settingsButtonPressed.value =
                                !viewState.settingsButtonPressed.value
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
                    visible = init && !viewState.drawPolygonButtonPressed.value,
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
                            viewState.drawPolygonButtonPressed.value = true
                            viewState.settingsButtonPressed.value = false
                            viewModel.onIntent(MapScreenIntents.DrawPolygon)
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