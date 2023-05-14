package hr.bornaseatovic.myapplication.main.features.calculation.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings

@Composable
fun MapScreen (
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    var uiSettings by remember {
        mutableStateOf(MapUiSettings())
    }
    var properties by remember {
        mutableStateOf(MapProperties(
            mapType = MapType.NORMAL
        ))
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        GoogleMap(modifier = Modifier.matchParentSize()) {

        }
    }
}