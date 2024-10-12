package com.vondi.passmanager.presentation.components

import android.widget.CheckBox
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.R
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Shape
import com.vondi.passmanager.presentation.common.TextColor
import com.vondi.passmanager.ui.theme.Red

@Composable
fun PassGeneration(

) {

    val rangeSlider = 0f..100f
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var checked1  by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(true) }
    var checked3 by remember { mutableStateOf(true) }
    var isParametersVisible by remember { mutableStateOf(false) }

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
                Slider(
                    value = sliderPosition,
                    onValueChange = { newValue -> sliderPosition = newValue },
                    valueRange = rangeSlider,
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(disabledThumbColor = PrimaryColor)
                )

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
