package com.vondi.passmanager.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryColor: Color
    @Composable
    get() = MaterialTheme.colorScheme.primary

val SecondaryColor: Color
    @Composable
    get() = MaterialTheme.colorScheme.secondary

val BackgroundColor: Color
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

val ErrorColor: Color
    @Composable
    get() = MaterialTheme.colorScheme.error
