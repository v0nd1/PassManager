package com.vondi.passmanager.presentation.screens.pinlock

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vondi.passmanager.data.util.KeystoreManager
import com.vondi.passmanager.domain.event.PinLockEvent
import com.vondi.passmanager.domain.model.pinlock.PinLockState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PinLockViewModel(
    private val keystoreManager: KeystoreManager
) : ViewModel() {

    private val _state = MutableStateFlow(PinLockState())
    val state: StateFlow<PinLockState> = _state

    init {
        val pinSet = keystoreManager.getPin().isNullOrBlank()
        _state.update {
            it.copy(
                isAuthenticated = !pinSet
            )
        }
    }

    fun onEvent(event: PinLockEvent) {
        when (event) {
            is PinLockEvent.DeleteDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.substring(0, it.inputPin.length - 1)
                    )
                }
            }
            is PinLockEvent.AddDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.plus(event.digit)
                    )
                }
            }
            is PinLockEvent.ClearPin -> {
                _state.update {
                    it.copy(
                        inputPin = ""
                    )
                }
            }
            is PinLockEvent.CheckPin -> {
                val currentState = _state.value
                if (currentState.inputPin.length == 4) {
                    if (currentState.isAuthenticated) {
                        val storedPin = keystoreManager.getPin()
                        if (currentState.inputPin == storedPin) {
                            _state.update {
                                it.copy(
                                    error = ErrorPin.SUCCESS
                                )
                            }
                            Log.d("PinLock", "Here ${_state.value.error}")
                        } else {
                            _state.update {
                                it.copy(
                                    error = ErrorPin.INCORRECT_PASS
                                )
                            }
                            Log.d("PinLock", "Here ${currentState.error}")

                            onEvent(PinLockEvent.ClearPin)
                        }
                    } else {
                        if (currentState.confirmPin.isNullOrBlank()) {
                            _state.update {
                                it.copy(
                                    confirmPin = currentState.inputPin,
                                    error = ErrorPin.TRY_PIN
                                )
                            }
                            Log.d("PinLock", "Here ${currentState.error}")
                            onEvent(PinLockEvent.ClearPin)
                        } else {
                            if (currentState.inputPin == currentState.confirmPin) {
                                keystoreManager.savePin(currentState.inputPin)
                                _state.value = currentState.copy(
                                    isAuthenticated = true,
                                    error = ErrorPin.SUCCESS,
                                    confirmPin = null
                                )
                                Log.d("PinLock", "Here ${currentState.error}")
                            } else {
                                _state.value = currentState.copy(error = ErrorPin.INCORRECT_PASS, confirmPin = null)
                                Log.d("PinLock", "Here ${currentState.error}")
                                onEvent(PinLockEvent.ClearPin)
                            }
                        }
                    }
                } else {
                    _state.value = currentState.copy(error = ErrorPin.NOT_ENOUGH_DIG)
                    onEvent(PinLockEvent.ClearPin)
                }
                Log.d("PinLock", "ConfPin = ${currentState.confirmPin} " +
                        "InpPin = ${currentState.inputPin} IsAuth = ${currentState.isAuthenticated} " +
                        "Error = ${_state.value.error}")
            }
        }
    }


}

enum class ErrorPin(val error: Int){
    SUCCESS(0),
    INCORRECT_PASS(1),
    NOT_ENOUGH_DIG(2),
    TRY_PIN(3)
}
