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
            is CalculatorIntents.PromjeniMjesecnaPotrosnja -> {

            }
            is CalculatorIntents.Promjeni1Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja1mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni2Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja2mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni3Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja3mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni4Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja4mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni5Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja5mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni6Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja6mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni7Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja7mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni8Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja8mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni9Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja9mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni10Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja10mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni11Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja11mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            is CalculatorIntents.Promjeni12Mjesec -> {
                internalState.value = internalState.value.copy(
                    potrosnja12mjesec = intent.potrosnja
                )
                saveConsumption()
            }
            CalculatorIntents.IzracunajPVGIS -> {
                viewModelScope.launch(Dispatchers.IO) {
                    calculatePVGIS()
                }
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
                    saveConsumption()
                }
            }
        }
    }

    private suspend fun getProduction() {
        val response = localDataSource.getProduction()
        internalState.value = internalState.value.copy(
            monthlyProduction = response?.monthlyProduction,
            yearlyProduction = response?.yearlyProduction ?: 0.0
        )
    }

    private fun saveConsumption() {
        val completeConsumption =
            internalState.value.potrosnja1mjesec.toDouble() +
            internalState.value.potrosnja2mjesec.toDouble() +
            internalState.value.potrosnja3mjesec.toDouble() +
            internalState.value.potrosnja4mjesec.toDouble() +
            internalState.value.potrosnja5mjesec.toDouble() +
            internalState.value.potrosnja6mjesec.toDouble() +
            internalState.value.potrosnja7mjesec.toDouble() +
            internalState.value.potrosnja8mjesec.toDouble() +
            internalState.value.potrosnja9mjesec.toDouble() +
            internalState.value.potrosnja10mjesec.toDouble() +
            internalState.value.potrosnja11mjesec.toDouble() +
            internalState.value.potrosnja12mjesec.toDouble()
        val monthlyConsumption = listOf(
            internalState.value.potrosnja1mjesec.toDouble(),
            internalState.value.potrosnja2mjesec.toDouble(),
            internalState.value.potrosnja3mjesec.toDouble(),
            internalState.value.potrosnja4mjesec.toDouble(),
            internalState.value.potrosnja5mjesec.toDouble(),
            internalState.value.potrosnja6mjesec.toDouble(),
            internalState.value.potrosnja7mjesec.toDouble(),
            internalState.value.potrosnja8mjesec.toDouble(),
            internalState.value.potrosnja9mjesec.toDouble(),
            internalState.value.potrosnja10mjesec.toDouble(),
            internalState.value.potrosnja11mjesec.toDouble(),
            internalState.value.potrosnja12mjesec.toDouble()
        )
        internalState.value = internalState.value.copy(
            completeConsumption = completeConsumption,
            monthlyConsumption = monthlyConsumption
        )
    }
}
