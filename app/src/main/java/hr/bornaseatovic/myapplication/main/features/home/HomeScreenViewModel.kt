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
            HomeScreenIntents.OpenMap -> {
                viewModelScope.launch {
                    delay(180)
                    navigationManager.navigate(HomeDestinations.mapScreen())
                }
            }
        }
    }

}
