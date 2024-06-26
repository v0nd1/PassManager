package com.vondi.passmanager.domain.model.pinlock

data class PinLockState(
    val isAuthenticated: Boolean = false,
    val inputPin: String = "",
    val error: String = ""
)
