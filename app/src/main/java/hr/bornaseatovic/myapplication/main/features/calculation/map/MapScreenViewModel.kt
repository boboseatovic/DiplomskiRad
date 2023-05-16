package hr.bornaseatovic.myapplication.main.features.calculation.map

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.RemoteDataSource
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val remoteDataSource: RemoteDataSource
): BaseViewModel<MapScreenViewState, MapScreenIntents>(){
    override val initialState = MapScreenViewState()


    override fun onIntent(intent: MapScreenIntents) {
        when(intent) {
            MapScreenIntents.GoBack -> {
                navigationManager.goBack()
            }
            is MapScreenIntents.InputLatitude -> {
                internalState.value = internalState.value.copy(
                    latitudeValue = intent.latitudeValue
                )
            }
            is MapScreenIntents.InputLongitude -> {
                internalState.value = internalState.value.copy(
                    longitudeValue = intent.longitudeValue
                )
            }
            is MapScreenIntents.ChangeSearchValue -> {
                internalState.value = internalState.value.copy(
                    searchValue = intent.searchValue
                )
            }
            MapScreenIntents.Search -> {
                viewModelScope.launch(Dispatchers.IO) {
                    fetchGeolocation()
                }
            }
        }
    }



    private suspend fun fetchGeolocation() {
        remoteDataSource.fetchGeolocation(internalState.value.searchValue).collect { response ->
            when(response) {
                is Resource.Error -> {
                    Log.i("TAG", "fetchGeolocation: Error")
                }
                is Resource.Success -> {
                    Log.i("TAG", "fetchGeolocation: Success")
                    internalState.value = internalState.value.copy(
                        location = mutableStateOf(
                            LatLng(
                                response.data?.latitude ?: internalState.value.location.value.latitude,
                                response.data?.longitude ?: internalState.value.location.value.longitude
                            )
                        )
                    )

                }
            }
        }
    }

}
