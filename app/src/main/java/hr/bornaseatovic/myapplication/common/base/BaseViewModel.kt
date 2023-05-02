package hr.bornaseatovic.myapplication.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VS, INTENT>: ViewModel() {

    abstract val initialState: VS
    protected val internalState: MutableStateFlow<VS> by lazy { MutableStateFlow(initialState) }
    val viewState: StateFlow<VS>
        get() = internalState
    abstract fun onIntent(intent: INTENT)
}