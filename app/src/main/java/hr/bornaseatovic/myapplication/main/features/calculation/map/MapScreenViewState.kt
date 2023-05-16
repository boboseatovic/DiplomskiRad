package hr.bornaseatovic.myapplication.main.features.calculation.map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.maps.model.LatLng

data class MapScreenViewState(
    val latitudeValue: String = "",
    val longitudeValue: String = "",
    val searchValue: String = "",
    val location: MutableState<LatLng> = mutableStateOf(
        LatLng(45.8150, 15.9819)
    ),

)
