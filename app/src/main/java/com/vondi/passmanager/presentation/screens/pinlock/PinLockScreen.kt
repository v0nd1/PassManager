package com.vondi.passmanager.presentation.screens.pinlock

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vondi.passmanager.R
import com.vondi.passmanager.ui.theme.LightGreen

@Composable
fun PinLockScreen(navController: NavController) {
    Keyboard(
        navController = navController
    )
}


@Composable
private fun Keyboard(
    navController: NavController
) {
    var enteredPin by remember { mutableStateOf("") }
    val correctedPin = "1234"
    val listKeys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("del", "0", "OK")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(4) { index ->
                val isFilled = index < enteredPin.length
                PinDot(isFilled = isFilled)
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        listKeys.forEach { rows ->
            Row {
                rows.forEach {
                    PinKeyItem(
                        onClick = {
                            when (it) {
                                "del" -> enteredPin.dropLast(1)
                                "OK" -> {
                                    if (enteredPin == correctedPin) {
                                        navController.navigate("mainScreen")
                                    } else {
                                        enteredPin = ""
                                        Log.d("error", "Pin is uncorrected $enteredPin")
                                    }
                                }

                                else -> {
                                    if (enteredPin.length < 4) enteredPin += it
                                    Log.d("pin", enteredPin)
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
                                        .size(30.dp)
                                )
                            }

                            "OK" -> {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Success",
                                    modifier = Modifier
                                        .size(35.dp)
                                )
                            }

                            else -> Text(
                                text = it,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium
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
            .background(if (isFilled) LightGreen else Color.Gray, CircleShape)
    )
}

@Composable
private fun PinKeyItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(8.dp),
    shape: Shape = RoundedCornerShape(100),
    backgroundColor: Color = LightGreen,
    contentColor: Color = Color.Black,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.defaultMinSize(minWidth = 95.dp, minHeight = 95.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}