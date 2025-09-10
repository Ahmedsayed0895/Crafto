package org.example.project.presentation.viewmodle.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<SCREEN_STATE, SCREEN_EFFECT>(
    initialState: SCREEN_STATE,
) : ViewModel() {
    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<SCREEN_EFFECT>()
    val effect = _effect.asSharedFlow()

    protected fun <T> tryToCall(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (error: ErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = call()
                onSuccess(result)
            } catch (e: Exception) {
                onError(ErrorUiState(e.message ?: "Unknown error"))
            }
        }
    }



    protected fun updateState(updater: (SCREEN_STATE) -> SCREEN_STATE) = _state.update(updater)

    protected fun sendNewEffect(newEffect: SCREEN_EFFECT) {
        viewModelScope.launch() {
            _effect.emit(newEffect)
        }
    }
}