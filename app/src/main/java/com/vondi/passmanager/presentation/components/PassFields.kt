package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.R
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Shape
import com.vondi.passmanager.presentation.common.Size
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

@Composable
fun PassCopyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onClick: () -> Unit
) {

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
        singleLine = true,
        shape = RoundedCornerShape(Shape.Small),
        trailingIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = stringResource(R.string.copy),
                    modifier = Modifier.size(Size.Small),
                    tint = TextColor
                )
            }
        },
    )

}


@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onClick: () -> Unit
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
        shape = RoundedCornerShape(Shape.Small),
        trailingIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.Key ,
                    contentDescription = stringResource(R.string.generate_pass),
                    tint = PrimaryColor
                )
            }
        }
    )

}


@Composable
fun CategoryField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onClick: () -> Unit
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
        shape = RoundedCornerShape(Shape.Small),
        trailingIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown ,
                    contentDescription = stringResource(R.string.categorylist),
                    tint = PrimaryColor
                )
            }

        }

    )

}