package com.vondi.passmanager.presentation.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.R
import com.vondi.passmanager.presentation.common.Container
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.Primary
import com.vondi.passmanager.presentation.common.Size
import com.vondi.passmanager.presentation.common.Tertiary
import com.vondi.passmanager.presentation.common.TextColor


sealed class Category(
    val name: String,
    val selected: Boolean
) {
    companion object {
        fun allCategories(): List<Category> {
            return listOf(
                Sites,
                Accounts,
                Browsers
            )
        }
    }
    data object Sites: Category(
        name = "Sites",
        selected = true
    )

    data object Accounts: Category(
        name = "Accounts",
        selected = false
    )

    data object Browsers: Category(
        name = "Browsers",
        selected = false
    )
}

@Composable
fun CategoryList(
    categories: List<Category> = Category.allCategories()
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(start = Dimens.Small)
            .width(200.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20))
            .background(Tertiary)
            .clickable { expanded = true }

    ){
        Column {
            Row(
                Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                categories.forEach{
                    if (it.selected) {
                        Text(
                            text = it.name,
                            fontSize = FontSize.SmallMedium,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = Dimens.Small)
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.dropdownmenu),
                    modifier = Modifier
                        .size(Size.Medium)
                        .padding(end = Dimens.Small),
                    tint = TextColor
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(200.dp)
                    .wrapContentHeight()
                    .background(Tertiary)


            ) {
                categories.forEach {
                    DropdownMenuItem(
                        onClick = { },
                        text = {
                            Text(
                                text = it.name,
                                color = TextColor,
                                fontSize = FontSize.SmallMedium
                            )
                        },
                        trailingIcon = {
                            if (it.selected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = stringResource(R.string.selected_category)
                                )
                            }

                        }
                    )
                }
            }
        }



    }

}