package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Shape
import com.vondi.passmanager.presentation.common.Tertiary
import com.vondi.passmanager.presentation.common.TextColor
import kotlin.math.max

@Composable
fun PassTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    val maxLines = 1
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedTextColor = TextColor,
            focusedTextColor = TextColor,
            unfocusedBorderColor = PrimaryColor,
            focusedBorderColor = PrimaryColor
        ),
        maxLines = maxLines,
        shape = RoundedCornerShape(Shape.Small)
    )

}