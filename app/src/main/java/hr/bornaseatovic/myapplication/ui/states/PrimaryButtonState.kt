package hr.bornaseatovic.myapplication.ui.states

sealed class PrimaryButtonState {
    object NormalButton : PrimaryButtonState()
    object LoadingButton : PrimaryButtonState()
    data class DisabledButton(val disabledText: String) : PrimaryButtonState()
}
