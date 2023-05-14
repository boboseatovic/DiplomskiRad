package hr.bornaseatovic.myapplication.main.features.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import hr.bornaseatovic.myapplication.main.navigation.destinations.HomeDestinations
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : BaseViewModel<HomeScreenViewState, HomeScreenIntents>() {
    override val initialState = HomeScreenViewState()

    override fun onIntent(intent: HomeScreenIntents) {
        when (intent) {
            HomeScreenIntents.PressCalculateNew -> {
                viewModelScope.launch {
                    delay(600)
                    navigationManager.navigate(HomeDestinations.mapScreen())
                }
            }
            HomeScreenIntents.CloseMap -> {
                viewModelScope.launch {
                    internalState.value = internalState.value.copy(
                        buttonClicked = mutableStateOf(false),
                        textAnimation = mutableStateOf(false)
                    )
                    delay(400)
                    internalState.value =internalState.value.copy(
                        mapVisible = mutableStateOf(false)
                    )
                }
            }
            HomeScreenIntents.OpenMap -> {
                viewModelScope.launch {
                    internalState.value = internalState.value.copy(
                        textAnimation = mutableStateOf(true)
                    )
                    delay(400)
                    internalState.value =internalState.value.copy(
                        buttonClicked = mutableStateOf(true),
                        mapVisible = mutableStateOf(true)
                    )
                }
            }
        }
    }

}
