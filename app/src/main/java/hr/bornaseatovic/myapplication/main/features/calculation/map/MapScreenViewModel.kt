package hr.bornaseatovic.myapplication.main.features.calculation.map

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager
): BaseViewModel<MapScreenViewState, MapScreenIntents>(){
    override val initialState = MapScreenViewState()


    override fun onIntent(intent: MapScreenIntents) {
        when(intent) {
            MapScreenIntents.GoBack -> {
                navigationManager.goBack()
            }
        }
    }

}
