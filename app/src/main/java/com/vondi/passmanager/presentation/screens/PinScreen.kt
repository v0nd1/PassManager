//package com.vondi.passmanager.presentation.screens
//
//import android.content.Context
//import android.graphics.drawable.shapes.Shape
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.material3.Text
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.interaction.collectIsPressedAsState
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.defaultMinSize
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Check
//import androidx.compose.material.ripple.rememberRipple
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ProvideTextStyle
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.contentColorFor
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.drawWithContent
//import androidx.compose.ui.geometry.CornerRadius
//import androidx.compose.ui.geometry.center
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.semantics.Role
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.rememberLottieComposition
//import com.vondi.passmanager.ui.theme.Black
//import com.vondi.passmanager.ui.theme.DarkGreen
//import com.vondi.passmanager.ui.theme.Green
//import com.vondi.passmanager.ui.theme.LightGreen
//import com.vondi.passmanager.ui.theme.White
//import kotlinx.coroutines.delay
//import xyz.teamgravity.pin_lock_compose.PinLock
//
//const val pinSize = 4
//const val password = "1234" //sample password
//
//@Composable
//fun PinLockScreen() {
//    val inputPin = remember { mutableStateListOf<Int>() }
//    val error = remember { mutableStateOf<String>("") }
//    val showSuccess = remember { mutableStateOf(false) }
//    val context = LocalContext.current
//
//    if (inputPin.size == 4) {
//        LaunchedEffect(true) {
//            delay(300)
//
//            if (inputPin.joinToString("") == password) {
//                showSuccess.value = true
//                error.value = ""
//            } else {
//                inputPin.clear()
//                error.value = "Wrong pin, Please retry!"
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Enter pin to unlock",
//                    modifier = Modifier.padding(16.dp),
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(30.dp))
//
//                if (showSuccess.value) {
//                    LottieLoadingView(
//                        context = context,
//                        file = "success.json",
//                        iterations = 1,
//                        modifier = Modifier.size(100.dp)
//                    )
//                } else {
//                    Row {
//                        (0 until pinSize).forEach {
//                            Icon(
//                                imageVector = if (inputPin.size > it) Icons.Default.Circle else Icons.Outlined.Circle,
//                                contentDescription = it.toString(),
//                                modifier = Modifier
//                                    .padding(8.dp)
//                                    .size(30.dp),
//                                tint = Color.Black
//                            )
//                        }
//                    }
//                }
//                Text(
//                    text = error.value,
//                    color = Green,
//                    modifier = Modifier.padding(16.dp)
//                )
//
//                Spacer(modifier = Modifier.height(50.dp))
//            }
//
//            Column(
//                modifier = Modifier
//                    .wrapContentSize()
//                    .align(Alignment.BottomCenter)
//                    .padding(bottom = 20.dp)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    (1..3).forEach {
//                        PinKeyItem(
//                            onClick = { inputPin.add(it) }
//                        ) {
//                            Text(
//                                text = it.toString(),
//                            )
//                        }
//                    }
//                }
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    (4..6).forEach {
//                        PinKeyItem(
//                            onClick = { inputPin.add(it) }
//                        ) {
//                            Text(
//                                text = it.toString(),
//                            )
//                        }
//                    }
//                }
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    (7..9).forEach {
//                        PinKeyItem(
//                            onClick = { inputPin.add(it) }
//                        ) {
//                            Text(
//                                text = it.toString(),
//                                modifier = Modifier.padding(4.dp)
//                            )
//                        }
//                    }
//                }
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Check,
//                        contentDescription = "Success",
//                        modifier = Modifier
//                            .size(25.dp)
//                            .clickable { }
//                    )
//                    PinKeyItem(
//                        onClick = { inputPin.add(0) },
//                        modifier = Modifier.padding(horizontal = 16.dp,
//                            vertical = 8.dp)
//                    ) {
//                        Text(
//                            text = "0",
//                            modifier = Modifier.padding(4.dp)
//                        )
//                    }
//                    Icon(
//                        imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "Clear",
//                        modifier = Modifier
//                            .size(25.dp)
//                            .clickable {
//                                if (inputPin.isNotEmpty()) {
//                                    inputPin.removeLast()
//                                }
//                            }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LottieLoadingView(
//    context: Context,
//    file: String,
//    modifier: Modifier = Modifier,
//    iterations: Int = 10
//) {
//    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(file))
//    LottieAnimation(
//        composition = composition,
//        modifier = modifier.defaultMinSize(300.dp),
//        iterations = iterations
//    )
//}
//
//
//@Composable
//fun PinLockHuek(){
//    PinLock(
//        title = { pinExists ->
//            Text(text = if (pinExists) "Enter your pin" else "Create pin")
//        },
//        color = MaterialTheme.colorScheme.primary,
//        onPinCorrect = {
//            // pin is correct, navigate or hide pin lock
//        },
//        onPinIncorrect = {
//            // pin is incorrect, show error
//        },
//        onPinCreated = {
//            // pin created for the first time, navigate or hide pin lock
//        }
//    )
//}