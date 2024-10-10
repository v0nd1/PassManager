package com.vondi.passmanager.presentation.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.ErrorColor
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.Size
import com.vondi.passmanager.presentation.components.PassButton
import com.vondi.passmanager.presentation.components.PassSurface
import com.vondi.passmanager.presentation.components.PassTextField

@Composable
fun AddItemScreen(
    navController: NavController,
    onEvent: (ItemEvent) -> Unit,
    state: ItemState
) {
    PassSurface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.Medium, vertical = Dimens.Medium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_to_item_screen)
                    )
                }
            }
            Spacer(Modifier.height(Dimens.Medium))
            Text(
                text = "Создайте свою карточку",
                fontSize = FontSize.Big
            )
            Spacer(Modifier.height(Dimens.Small))
            ColorPalitra()
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
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

                PassTextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = "Пароль"
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PassButton(
                    text = "Отмена",
                    background = ErrorColor
                ){

                }

                PassButton(
                    text = "Создать"
                ){

                }
            }
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
                    .border(width = 1.dp, shape = RoundedCornerShape(100), color = Color.White)
                    .clip(RoundedCornerShape(100))
                    .clickable { }
                    .background(color = it)

            )
        }
    }

}