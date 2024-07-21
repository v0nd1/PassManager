package com.vondi.passmanager.domain.model.pinlock

import com.vondi.passmanager.presentation.screens.pinlock.ErrorPin

data class PinLockState(
    val isAuthenticated: Boolean = false,
    val inputPin: String = "",
    val error: ErrorPin = ErrorPin.SUCCESS,
    val confirmPin: String? = null
)
