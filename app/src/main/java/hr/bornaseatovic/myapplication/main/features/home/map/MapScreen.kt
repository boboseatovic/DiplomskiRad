package hr.bornaseatovic.myapplication.main.features.home.map

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import hr.bornaseatovic.myapplication.R
import hr.bornaseatovic.myapplication.main.features.home.HomeScreenIntents
import hr.bornaseatovic.myapplication.main.features.home.HomeScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    viewModel: HomeScreenViewModel,
    mapAlpha: Float
) {
    val viewState = viewModel.viewState.collectAsState().value


    val myLocation = LatLng(45.8150, 15.9819)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myLocation, 10f)
    }

    Box(
        modifier = Modifier
            .alpha(mapAlpha)
            .clickable(
                enabled = false,
                onClick = {}
            )
            .fillMaxSize()
            .background(Color.Black)
    ) {

        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        )
        Box(
            modifier = Modifier
                .padding(20.dp)
//                .offset(x =)
//                .alpha()
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                   viewModel.onIntent(HomeScreenIntents.CloseMap)
                }
                .size(40.dp)
                .align(Alignment.TopEnd)
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