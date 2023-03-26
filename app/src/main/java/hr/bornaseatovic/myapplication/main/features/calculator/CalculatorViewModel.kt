package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.bornaseatovic.myapplication.common.base.BaseViewModel
import hr.bornaseatovic.myapplication.data.dataSource.LocalDataSource
import hr.bornaseatovic.myapplication.data.model.ExampleDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
): BaseViewModel<CalculatorViewState, CalculatorIntents>() {
    override val initialState = CalculatorViewState()


    override fun onIntent(intent: CalculatorIntents) {
        when(intent) {
            CalculatorIntents.PressAButton -> {
                viewModelScope.launch(Dispatchers.IO) {
                    localDataSource.insertExampleDb(ExampleDb(
                        key = 0,
                        example = "Example"
                    ))
                    internalState.value = internalState.value.copy(
                        tekst = localDataSource.getExampleDb().example
                    )
                }
            }
        }
    }

}
