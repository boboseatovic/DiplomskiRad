package hr.bornaseatovic.myapplication.main.features.calculator

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.LocalDataSource
import hr.bornaseatovic.myapplication.data.dataSource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
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

            CalculatorIntents.NavigateToFourthScreen -> {

            }
            is CalculatorIntents.PromjeniMjesecnaPotrosnja -> {

            }
            is CalculatorIntents.Promjeni1Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja1mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni2Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja2mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni3Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja3mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni4Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja4mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni5Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja5mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni6Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja6mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni7Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja7mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni8Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja8mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni9Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja9mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni10Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja10mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni11Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja11mjesec = intent.potrosnja
                )
            }
            is CalculatorIntents.Promjeni12Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja12mjesec = intent.potrosnja
                )
            }
            CalculatorIntents.IzracunajPVGIS -> {
                viewModelScope.launch(Dispatchers.IO) {
                    calculatePVGIS()
                }
            }
            CalculatorIntents.InitThirdScreen -> {
//                viewModelScope.launch(Dispatchers.IO) {
//                    getProduction()
//                }
            }
        }
    }

    private suspend fun calculatePVGIS() {
        remoteDataSource.fetchPVCalculations(
            internalState.value.geoSirina.toFloat(),
            internalState.value.geoDuzina.toFloat()
        ).collect { result ->
            when (result) {
                is Resource.Error -> {
                    Log.i("TAG", "calculatePVGIS: Error")
                }
                is Resource.Success -> {
                    Log.i("TAG", "calculatePVGIS: Success")
                    viewModelScope.launch(Dispatchers.IO) {
                        getProduction()
                    }
                }
            }
        }
    }

    private suspend fun getProduction() {
        val response = localDataSource.getProduction()
        internalState.value = internalState.value.copy(
            monthlyProduction = response?.monthlyProduction,
        )
    }
}
