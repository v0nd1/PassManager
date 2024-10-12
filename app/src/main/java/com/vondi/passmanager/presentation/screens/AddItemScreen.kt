package com.vondi.passmanager.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.ErrorColor
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Shape
import com.vondi.passmanager.presentation.common.Size
import com.vondi.passmanager.presentation.common.TextColor
import com.vondi.passmanager.presentation.components.CategoryField
import com.vondi.passmanager.presentation.components.PassButton
import com.vondi.passmanager.presentation.components.PassGeneration
import com.vondi.passmanager.presentation.components.PassSurface
import com.vondi.passmanager.presentation.components.PassTextField
import com.vondi.passmanager.presentation.components.PasswordField
import com.vondi.passmanager.presentation.navigation.Screen

@Composable
fun AddItemScreen(
    navController: NavController,
    onEvent: (ItemEvent) -> Unit,
    state: ItemState
) {

    val verticalScroll = rememberScrollState()
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
                CategoryField(
                    value = state.category,
                    onValueChange = {
                        //onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = "Категория"
                )
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

                PasswordField(
                    value = state.password,
                    onValueChange = {
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = "Пароль"
                )
                PassGeneration()

            }


            Spacer(Modifier.height(Dimens.Big))
            Text(
                text = "Выберите цвет\nкарточки",
                fontSize = FontSize.Medium
            )
            Spacer(Modifier.height(Dimens.Small))
            ColorPalitra()

            Spacer(Modifier.height(Dimens.Big))

            Row (
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
                        text = "Отмена",
                        color = White
                    )
                }

                PassButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Сохранить",
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
    colors: List<Color> = listOf(Color.Red, Color.Green, Color.Blue, Color.Black, Color.Yellow, Color.Magenta, Color.LightGray, Color.Cyan)
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        maxItemsInEachRow = 9
    ) {
        colors.forEach {
            Box(
                modifier = Modifier
                    .padding(end = Dimens.ExtraSmall, bottom = Dimens.ExtraSmall)
                    .size(Size.Medium)
                    .border(width = 1.dp, shape = RoundedCornerShape(100), color = TextColor)
                    .clip(RoundedCornerShape(100))
                    .clickable { }
                    .background(color = it)

            )
        }
    }

}