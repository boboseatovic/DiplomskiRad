package hr.bornaseatovic.myapplication.main.features.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.RemoteDataSource
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import hr.bornaseatovic.myapplication.main.navigation.destinations.HomeDestinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val remoteDataSource: RemoteDataSource
) : BaseViewModel<HomeScreenViewState, HomeScreenIntents>() {
    override val initialState = HomeScreenViewState()

    override fun onIntent(intent: HomeScreenIntents) {
        when (intent) {
            HomeScreenIntents.OpenMap -> {
                viewModelScope.launch(Dispatchers.IO){
                    remoteDataSource.fetchGeolocation("Nasicka Ulica 61D").collect { resource ->
                        when (resource) {
                            is Resource.Error -> {

                            }
                            is Resource.Success -> {
                                internalState.value = internalState.value.copy(
                                    text = resource.data?.latitude?.toString() ?: "Nista"
                                )
                            }
                        }

                    }
                }
//                viewModelScope.launch {
//                    delay(180)
//                    navigationManager.navigate(HomeDestinations.mapScreen())
//                }
            }
        }
    }

}
