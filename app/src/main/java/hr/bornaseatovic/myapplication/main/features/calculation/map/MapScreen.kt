package hr.bornaseatovic.myapplication.main.features.calculation.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import hr.bornaseatovic.myapplication.R

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
                .padding(horizontal = 24.dp, vertical = 40.dp)
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
                        .clip(RoundedCornerShape(8.dp))
                        .height(45.dp)
                        .weight(1f)
                        .background(MaterialTheme.colors.background),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nesto",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 15.dp)
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
                        .padding(start = 5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            viewModel.onIntent(MapScreenIntents.GoBack)
                        }
                        .size(45.dp)
                        .background(MaterialTheme.colors.background)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "",
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            }

        }
    }
}