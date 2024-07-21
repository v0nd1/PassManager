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
        _state.value = _state.value.copy(isAuthenticated = !pinSet)
    }

    fun onEvent(event: PinLockEvent) {
        when (event) {
            is PinLockEvent.DeleteDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.substring(0, it.inputPin.length - 1)
                    )
                }
                Log.d("PinLockViewModel", _state.value.inputPin)
            }
            is PinLockEvent.AddDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.plus(event.digit)
                    )
                }
                Log.d("PinLock", "enter = ${event.digit} pin = ${_state.value.inputPin}")
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
                Log.d("PinLock", "ConfPin = ${currentState.confirmPin} InpPin = ${currentState.inputPin} IsAuth = ${currentState.isAuthenticated} Error = ${currentState.error}")
                if (currentState.inputPin.length == 4) {
                    if (currentState.isAuthenticated) {
                        val storedPin = keystoreManager.getPin()
                        if (currentState.inputPin == storedPin) {
                            Log.d("PinLOCKHERE", "KERE4")
                            _state.value = currentState.copy(error = ErrorPin.SUCCESS)

                        } else {
                            Log.d("PinLOCKHERE", "KERE5 ${currentState.error}")
                            _state.update {
                                it.copy(
                                    error = ErrorPin.INCORRECT_PASS
                                )
                            }
                            Log.d("PinLOCKHERE", "KERE52 ${currentState.error}")
                            onEvent(PinLockEvent.ClearPin)
                        }
                    } else {
                        if (currentState.confirmPin.isNullOrBlank()) {
                            Log.d("PinLOCKHERE", "KERE3")
                            _state.update {
                                it.copy(
                                    confirmPin = currentState.inputPin,
                                    error = ErrorPin.TRY_PIN
                                )
                            }
                            onEvent(PinLockEvent.ClearPin)
                        } else {
                            if (currentState.inputPin == currentState.confirmPin) {
                                keystoreManager.savePin(currentState.inputPin)
                                _state.value = currentState.copy(
                                    isAuthenticated = true,
                                    error = ErrorPin.SUCCESS,
                                    confirmPin = null
                                )
                            } else {
                                Log.d("PinLOCKHERE", "KERE1")
                                _state.value = currentState.copy(error = ErrorPin.INCORRECT_PASS, confirmPin = null)
                                onEvent(PinLockEvent.ClearPin)
                            }
                        }

                    }
                } else {
                    Log.d("PinLOCKHERE", "KERE2")
                    _state.value = currentState.copy(error = ErrorPin.NOT_ENOUGH_DIG)
                    onEvent(PinLockEvent.ClearPin)
                }
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
