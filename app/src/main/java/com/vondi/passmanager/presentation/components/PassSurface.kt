package com.vondi.passmanager.presentation.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PassSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier
    ) {
        content()
    }
}