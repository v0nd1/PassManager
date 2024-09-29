package com.vondi.passmanager.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary: Color
    @Composable
    get() = MaterialTheme.colorScheme.primary

val Secondary: Color
    @Composable
    get() = MaterialTheme.colorScheme.secondary

val Background: Color
    @Composable
    get() = MaterialTheme.colorScheme.background

val Tertiary: Color
    @Composable
    get() = MaterialTheme.colorScheme.tertiary

val Container: Color
    @Composable
    get() = MaterialTheme.colorScheme.primaryContainer

val TextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black
