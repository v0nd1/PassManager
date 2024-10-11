package com.vondi.passmanager.presentation.components

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.TextColor

@Composable
fun PassButton(
    background: Color = PrimaryColor,
    modifier: Modifier = Modifier,
    height: Dp = Dimens.Big,
    width: Dp = Dimens.ExtraLarge2,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(height)
            .width(width),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            containerColor = background,
            contentColor = TextColor
        )
    ) {
        content()
    }
}