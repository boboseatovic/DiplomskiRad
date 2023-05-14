package hr.bornaseatovic.myapplication.main.features.calculation.map

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager
): BaseViewModel<Unit, Unit>(){
    override val initialState = Unit


    override fun onIntent(intent: Unit) {
        TODO("Not yet implemented")
    }

}
