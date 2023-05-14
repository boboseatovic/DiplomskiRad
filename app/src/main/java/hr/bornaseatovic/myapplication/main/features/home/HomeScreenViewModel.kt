package hr.bornaseatovic.myapplication.main.features.home

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
) : BaseViewModel<Unit, HomeScreenIntents>() {
    override val initialState = Unit

    override fun onIntent(intent: HomeScreenIntents) {
        when (intent) {
            HomeScreenIntents.PressCalculateNew -> {
                viewModelScope.launch {
                    delay(600)
                    navigationManager.navigate(HomeDestinations.mapScreen())
                }
            }
        }
    }

}
