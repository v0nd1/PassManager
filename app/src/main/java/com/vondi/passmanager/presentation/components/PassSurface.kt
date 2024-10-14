package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vondi.passmanager.presentation.common.BackgroundColor
import com.vondi.passmanager.presentation.common.Dimens

@Composable
fun PassSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier,
        color = BackgroundColor
    ) {
        content()
    }
}