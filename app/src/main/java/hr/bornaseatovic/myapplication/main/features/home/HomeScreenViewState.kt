package hr.bornaseatovic.myapplication.main.features.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class HomeScreenViewState(
    var textAnimation: MutableState<Boolean> = mutableStateOf(false)
)
