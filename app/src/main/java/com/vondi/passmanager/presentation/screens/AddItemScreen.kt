package com.vondi.passmanager.presentation.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.ErrorColor
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Size
import com.vondi.passmanager.presentation.common.Tertiary
import com.vondi.passmanager.presentation.common.TextColor
import com.vondi.passmanager.presentation.components.CategoryField
import com.vondi.passmanager.presentation.components.PassButton
import com.vondi.passmanager.presentation.components.PassSurface
import com.vondi.passmanager.presentation.components.PassTextField
import com.vondi.passmanager.presentation.components.PasswordField
import com.vondi.passmanager.presentation.components.util.Category
import com.vondi.passmanager.ui.theme.Red
import kotlin.random.Random

@Composable
fun AddItemScreen(
    navController: NavController,
    onEvent: (ItemEvent) -> Unit,
    state: ItemState
) {

    val verticalScroll = rememberScrollState()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    PassSurface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.Medium, vertical = Dimens.Medium)
                .verticalScroll(verticalScroll)
        ) {
            Spacer(Modifier.height(Dimens.Big))
            Text(
                text = "Создайте новую\nкарточку",
                fontSize = FontSize.Big
            )

            Spacer(Modifier.height(Dimens.Medium))
            Text(
                text = "Введите данные",
                fontSize = FontSize.Medium
            )
            Spacer(Modifier.height(Dimens.Small))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column {
                    var categoryFieldWidth by remember { mutableIntStateOf(0) }
                    var categoryFieldHeight by remember { mutableIntStateOf(0) }
                    CategoryField(
                        value = state.category,
                        onValueChange = {
                            onEvent(ItemEvent.SetCategory(it))
                        },
                        placeholder = "Категория",
                        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                            categoryFieldWidth = layoutCoordinates.size.width
                            categoryFieldHeight = layoutCoordinates.size.height
                        }
                    ) {
                        if (state.categories.size > 1) {
                            expanded = true
                        }
                    }

                    DropdownMenu(
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(with(LocalDensity.current) { categoryFieldWidth.toDp() })
                            .heightIn(max = with(LocalDensity.current) { categoryFieldHeight.toDp() * 3 }),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        state.categories.filter { it.name != Category.All.name }
                            .forEach { category ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = category.name,
                                            fontSize = FontSize.Small,
                                            fontWeight = FontWeight.Medium
                                        )
                                    },
                                    onClick = {
                                        onEvent(ItemEvent.SetCategory(category.name))
                                        expanded = false
                                    }
                                )
                            }
                    }
                }


                PassTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(ItemEvent.SetName(it))
                    },
                    placeholder = "Наименование"
                )

                PassTextField(
                    value = state.url,
                    onValueChange = {
                        onEvent(ItemEvent.SetUrl(it))
                    },
                    placeholder = "Ссылка"
                )

                PassTextField(
                    value = state.login,
                    onValueChange = {
                        onEvent(ItemEvent.SetLogin(it))
                    },
                    placeholder = "Логин"
                )
                val rangeSlider = 8f..64f
                val steps = listOf(8f, 16f, 32f, 64f)
                var sliderPosition by remember { mutableFloatStateOf(8f) }
                var checked1  by remember { mutableStateOf(true) }
                var checked2 by remember { mutableStateOf(true) }
                var checked3 by remember { mutableStateOf(true) }
                var isParametersVisible by remember { mutableStateOf(false) }

                PasswordField(
                    value = state.password,
                    onValueChange = {
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = "Пароль"
                ){
                    if (!checked3 && !checked2 && !checked1 ) {
                        val randPass = generatePassword(
                            length = sliderPosition.toInt(),
                            includeLetters = true,
                            includeDigits = true,
                            includeSymbols = true
                        )
                        onEvent(ItemEvent.SetPassword(randPass))
                    } else {
                        val randPass = generatePassword(
                            length = sliderPosition.toInt(),
                            includeLetters = checked1,
                            includeDigits = checked2,
                            includeSymbols = checked3
                        )
                        onEvent(ItemEvent.SetPassword(randPass))

                    }

                }


                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isParametersVisible = !isParametersVisible },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Text(
                                text = "Параметры генерации",
                                color = PrimaryColor,
                                fontSize = FontSize.SmallMedium
                            )


                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = stringResource(R.string.settings_generation),
                            tint = PrimaryColor
                        )
                    }

                    AnimatedVisibility(
                        visible = isParametersVisible,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                steps.forEach { step ->
                                    Text(
                                        text = step.toInt().toString(),
                                        color = TextColor,
                                        fontSize = FontSize.Small
                                    )
                                }
                            }
                            Slider(
                                value = sliderPosition,
                                onValueChange = { newValue ->
                                    sliderPosition = newValue
                                },
                                valueRange = rangeSlider,
                                steps = steps.size - 2,
                                colors = SliderDefaults.colors(
                                    thumbColor = PrimaryColor,
                                    activeTrackColor = PrimaryColor
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(Dimens.Small))
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(Dimens.Small)
                            ) {
                                item {
                                    GenCheckBox(
                                        text = "С буквами",
                                        checked = checked1
                                    ) {
                                        checked1 = it
                                    }
                                }
                                item {
                                    GenCheckBox(
                                        text = "С цифрами",
                                        checked = checked2
                                    ) {
                                        checked2 = it
                                    }
                                }
                                item {
                                    GenCheckBox(
                                        text = "С символами",
                                        checked = checked3
                                    ) {
                                        checked3 = it
                                    }
                                }
                            }
                        }
                    }
                }

            }


            Spacer(Modifier.height(Dimens.Big))
            Text(
                text = "Выберите цвет\nкарточки",
                fontSize = FontSize.Medium
            )
            Spacer(Modifier.height(Dimens.Small))
            ColorPalitra(
                selectedColor = state.currentColor,
                onColorSelected = { color ->
                    onEvent(ItemEvent.SetCurColor(color))
                    onEvent(ItemEvent.SetColor(color.toArgb()))
                }
            )

            Spacer(Modifier.height(Dimens.Big))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PassButton(
                    onClick = { navController.popBackStack() },
                    background = ErrorColor
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = White
                    )
                }

                PassButton(onClick = {
                    if (state.url.isBlank() || state.name.isBlank() || state.password.isBlank() || state.login.isBlank()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.all_fields_mustbe),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        onEvent(ItemEvent.SaveItem)
                        navController.popBackStack()
                    }
                }) {
                    Text(
                        text = stringResource(R.string.save_pass),
                        color = White
                    )
                }
            }
            Spacer(Modifier.height(Dimens.ExtraMedium))

        }
    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColorPalitra(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    colors: List<Color> = listOf(
        Tertiary,
        Red,
        Color.Blue,
        Color.Black,
        Color.Yellow,
        Color.Magenta,
        Color.LightGray,
        Color.Cyan
    )
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        maxItemsInEachRow = 9
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .padding(end = Dimens.ExtraSmall, bottom = Dimens.ExtraSmall)
                    .size(Size.Medium)
                    .border(
                        width = if (color == selectedColor) 3.dp else 1.dp,
                        shape = RoundedCornerShape(100),
                        color = TextColor
                    )
                    .clip(RoundedCornerShape(100))
                    .clickable {
                        onColorSelected(color)
                    }
                    .background(color = color)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenCheckBox(
    text: String,
    checked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                modifier = Modifier.padding(end = Dimens.ExtraSmall),
                checked = checked,
                onCheckedChange = onChecked
            )
        }
        Text(
            text = text,
            color = TextColor,
            fontSize = FontSize.Small
        )
    }
}

private fun generatePassword(
    length: Int,
    includeLetters: Boolean,
    includeDigits: Boolean,
    includeSymbols: Boolean
): String {
    val letters = ('a'..'z') + ('A'..'Z')
    val digits = ('0'..'9')
    val symbols = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+')

    var charPool = mutableListOf<Char>()
    if (includeLetters) charPool += letters
    if (includeDigits) charPool += digits
    if (includeSymbols) charPool += symbols

    if (charPool.isEmpty()) charPool = letters.toMutableList()

    return (1..length)
        .map { Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}
