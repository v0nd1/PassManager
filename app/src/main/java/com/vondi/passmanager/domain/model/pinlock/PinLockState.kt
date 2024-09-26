package com.vondi.passmanager.domain.model.pinlock

import com.vondi.passmanager.presentation.viewmodels.ErrorPin

data class PinLockState(
    val isAuthenticated: Boolean = false,
    val inputPin: String = "",
    val error: ErrorPin? = null,
    val confirmPin: String? = null
)
