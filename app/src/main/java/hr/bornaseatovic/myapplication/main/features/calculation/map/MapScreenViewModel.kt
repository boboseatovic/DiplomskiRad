package hr.bornaseatovic.myapplication.main.features.calculation.map

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.common.domain.validation.ValidationUseCases
import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.RemoteDataSource
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val remoteDataSource: RemoteDataSource,
    private val validationUseCases: ValidationUseCases
) : BaseViewModel<MapScreenViewState, MapScreenIntents>() {
    override val initialState = MapScreenViewState()


    override fun onIntent(intent: MapScreenIntents) {
        when (intent) {
            MapScreenIntents.GoBack -> {
                navigationManager.goBack()
            }
            is MapScreenIntents.InputLatitude -> {
                if (validateNumber(intent.latitudeValue) || intent.latitudeValue.isBlank()) {
                    internalState.value = internalState.value.copy(
                        latitudeValue = intent.latitudeValue
                    )
                }
            }
            is MapScreenIntents.InputLongitude -> {
                if (validateNumber(intent.longitudeValue) || intent.longitudeValue.isBlank()) {
                    internalState.value = internalState.value.copy(
                        longitudeValue = intent.longitudeValue
                    )
                }
            }
            is MapScreenIntents.ChangeSearchValue -> {
                internalState.value = internalState.value.copy(
                    searchValue = intent.searchValue
                )
            }
            MapScreenIntents.SearchAddress -> {
                viewModelScope.launch(Dispatchers.IO) {
                    fetchGeolocation()
                }
            }
            MapScreenIntents.SearchLatLong -> {
                if (validateNumber(internalState.value.latitudeValue) && validateNumber(internalState.value.longitudeValue)) {
                    internalState.value = internalState.value.copy(
                        location = mutableStateOf(
                            LatLng(
                                internalState.value.latitudeValue.toDouble(),
                                internalState.value.longitudeValue.toDouble()
                            )
                        )
                    )
                }

            }
            MapScreenIntents.DrawPolygon -> {
                internalState.value = internalState.value.copy(
                    mapUiSettings = mutableStateOf(
                        MapUiSettings(
                            compassEnabled = false,
                            zoomControlsEnabled = false,
                            scrollGesturesEnabled = false
                        )
                    )
                )
            }
            MapScreenIntents.DoneDrawingPolygon -> {
                internalState.value = internalState.value.copy(
                    mapUiSettings = mutableStateOf(
                        MapUiSettings(
                            compassEnabled = false,
                            zoomControlsEnabled = false,
                            scrollGesturesEnabled = true
                        )
                    )
                )
            }
        }
    }


    private suspend fun fetchGeolocation() {
        remoteDataSource.fetchGeolocation(internalState.value.searchValue).collect { response ->
            when (response) {
                is Resource.Error -> {
                    Log.i("TAG", "fetchGeolocation: Error")
                }
                is Resource.Success -> {
                    Log.i("TAG", "fetchGeolocation: Success")
                    internalState.value = internalState.value.copy(
                        location = mutableStateOf(
                            LatLng(
                                response.data?.latitude
                                    ?: internalState.value.location.value.latitude,
                                response.data?.longitude
                                    ?: internalState.value.location.value.longitude
                            )
                        ),
                        latitudeValue = response.data?.latitude?.toString() ?: internalState.value.latitudeValue,
                        longitudeValue = response.data?.longitude?.toString() ?: internalState.value.longitudeValue,
                        searchValue = response.data?.label ?: internalState.value.searchValue
                    )

                }
            }
        }
    }

    private fun validateNumber(input: String): Boolean {
        return validationUseCases.numberValidation(input).isBlank()
    }
}
