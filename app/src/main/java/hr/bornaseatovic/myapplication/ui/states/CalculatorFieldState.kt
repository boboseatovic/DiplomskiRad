package hr.bornaseatovic.myapplication.ui.states

sealed interface CalculatorFieldState {
    object Normal : CalculatorFieldState
    object Disabled : CalculatorFieldState
    data class Error(val errorMessage: String) : CalculatorFieldState
}