package com.vondi.passmanager.presentation.screens.pinlock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.passmanager.data.util.KeystoreManager
import com.vondi.passmanager.domain.event.PinLockEvent
import com.vondi.passmanager.domain.model.pinlock.PinLockState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PinLockViewModel(
    private val keystoreManager: KeystoreManager
) : ViewModel() {

    private val _state = MutableStateFlow(PinLockState())
    val state: StateFlow<PinLockState> = _state

    fun onEvent(
        event: PinLockEvent
    ) {
        when (event) {
            is PinLockEvent.DeleteDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.dropLast(1)
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
        }
    }

}