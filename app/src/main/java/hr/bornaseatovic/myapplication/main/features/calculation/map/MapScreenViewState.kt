package hr.bornaseatovic.myapplication.main.features.calculation.map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings

data class MapScreenViewState(
    val latitudeValue: String = "",
    val latitudePlaceholderVisible: MutableState<Boolean> = mutableStateOf(true),
    val longitudeValue: String = "",
    val longitudePlaceholderVisible: MutableState<Boolean> = mutableStateOf(true),
    val searchValue: String = "",
    val searchPlaceholderVisible: MutableState<Boolean> = mutableStateOf(true),
    val location: MutableState<LatLng> = mutableStateOf(
        LatLng(45.8150, 15.9819)
    ),
    val addressButtonPressed: MutableState<Boolean> = mutableStateOf(true),
    val coordinatesButtonPressed: MutableState<Boolean> = mutableStateOf(false),
    val drawPolygonButtonPressed: MutableState<Boolean> = mutableStateOf(false),
    val searchPressed: MutableState<Boolean> = mutableStateOf(false),
    val animateSearch: MutableState<Boolean> = mutableStateOf(true),
    val animateCoordinates: MutableState<Boolean> = mutableStateOf(false),
    val settingsButtonPressed: MutableState<Boolean> = mutableStateOf(false),
    val mapUiSettings: MutableState<MapUiSettings> = mutableStateOf(
        MapUiSettings(
            compassEnabled = false,
            zoomControlsEnabled = false,
            scrollGesturesEnabled = true
        )
    )
)
