package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseViewModel<CalculatorViewState, CalculatorIntents>() {
    override val initialState = CalculatorViewState()
    override fun onIntent(intent: CalculatorIntents) {
        when (intent) {
            is CalculatorIntents.PromjeniGeoDuzina -> {
                internalState.value = internalState.value.copy(
                    geoDuzina = intent.duzina
                )
            }
            is CalculatorIntents.PromjeniGeoSirina -> {
                internalState.value = internalState.value.copy(
                    geoSirina = intent.sirina
                )
            }
            is CalculatorIntents.PromjeniNagibIstocnogKrova -> {
                internalState.value = internalState.value.copy(
                    nagibIstocnogKrova = intent.nagib
                )
            }
            is CalculatorIntents.PromjeniNagibJuznogKrova -> {
                internalState.value = internalState.value.copy(
                    nagibJunznogKrova = intent.nagib
                )
            }
            is CalculatorIntents.PromjeniNagibZapadnogKrova -> {
                internalState.value = internalState.value.copy(
                    nagibZapadnogKrova = intent.nagib
                )
            }
            is CalculatorIntents.PromjeniPovrsinaIstocnogKrova -> {
                internalState.value = internalState.value.copy(
                    povrsinaIstocnogKrova = intent.povrsina
                )
            }
            is CalculatorIntents.PromjeniPovrsinaJuznogKrova -> {
                internalState.value = internalState.value.copy(
                    povrsinaJunznogKrova = intent.povrsina
                )
            }
            is CalculatorIntents.PromjeniPovrsinaZapadnogKrova -> {
                internalState.value = internalState.value.copy(
                    povrsinaZapadnogKrova = intent.povrsina
                )
            }

            CalculatorIntents.PressAButton -> {
                viewModelScope.launch(Dispatchers.IO) {
                    remoteDataSource.fetchPVCalculations(
                        internalState.value.geoSirina.toFloat(),
                        internalState.value.geoDuzina.toFloat()
                    ).collect { result ->
                        when (result) {
                            is Resource.Error -> {

                            }
                            is Resource.Success -> {

                                internalState.value = internalState.value.copy(
                                    text = result.data?.totals?.fixed?.eD.toString()
                                )
                            }


                        }
                    }

                }
            }
            CalculatorIntents.NavigateToFourthScreen -> {

            }
        }
    }
}
