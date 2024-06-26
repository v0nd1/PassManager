package com.vondi.passmanager.domain.event

sealed interface PinLockEvent {

    data class AddDigit(val digit: String) : PinLockEvent
    data object DeleteDigit : PinLockEvent
    data object ClearPin : PinLockEvent
    data object Authenticate : PinLockEvent

}