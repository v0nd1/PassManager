package com.vondi.passmanager.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.PinLockEvent
import com.vondi.passmanager.domain.model.pinlock.PinLockState
import com.vondi.passmanager.presentation.navigation.Screen
import com.vondi.passmanager.presentation.viewmodels.ErrorPin
import com.vondi.passmanager.presentation.viewmodels.PinLockViewModel
import com.vondi.passmanager.ui.theme.White

@Composable
fun PinLockScreen(
    navController: NavController,
    viewModel: PinLockViewModel
) {
    val statePin by viewModel.state.collectAsState()
    LaunchedEffect(statePin.isAuthenticated, statePin.error) {
        if (statePin.isAuthenticated && statePin.error == ErrorPin.SUCCESS) {
            navController.popBackStack()
            navController.navigate(Screen.Item.route)
        }
    }
    Keyboard(
        onEvent = viewModel::onEvent,
        statePin = statePin
    )
}


@Composable
private fun Keyboard(
    onEvent: (PinLockEvent) -> Unit,
    statePin: PinLockState
) {
    val listKeys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("del", "0", "OK")
    )

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var text = when(statePin.error) {
            ErrorPin.INCORRECT_PASS -> "Попробуйте ещё раз"
            ErrorPin.TRY_PIN -> "Введите пин-код ещё раз для подтверждения"
            ErrorPin.NOT_ENOUGH_DIG -> "Недостаточно цифр, попробуйте еще раз"
            else -> "Введите пин-код"
        }
        if (!statePin.isAuthenticated && statePin.error == null) {
            text = "Для регистрации  введите пин-код"
        }
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(4) { index ->
                val isFilled = index < statePin.inputPin.length
                PinDot(isFilled = isFilled)
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        listKeys.forEach { rows ->
            Row {
                rows.forEach {
                    KeyButton(
                        onClick = {
                            when (it) {
                                "del" -> if (statePin.inputPin.isNotEmpty()) onEvent(PinLockEvent.DeleteDigit)
                                "OK" -> {
                                    onEvent(PinLockEvent.CheckPin)
                                }
                                else -> {
                                    if (statePin.inputPin.length < 4) onEvent(
                                        PinLockEvent.AddDigit(it)
                                    )
                                }
                            }
                        }
                    ) {
                        when (it) {
                            "del" -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.del_icon),
                                    contentDescription = "Clear",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }

                            "OK" -> {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Success",
                                    modifier = Modifier
                                        .size(35.dp),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }

                            else -> Text(
                                text = it,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PinDot(isFilled: Boolean) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(30.dp)
            .background(
                if (isFilled) MaterialTheme.colorScheme.tertiary else
                    if (isSystemInDarkTheme()) White
                    else Color.Gray, CircleShape
            )
    )
}

@Composable
private fun KeyButton(
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(100),
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape)
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(100)
            )
            .clickable(onClick = onClick)
            .defaultMinSize(minWidth = 95.dp, minHeight = 95.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.secondary) {
            content()
        }
    }
}