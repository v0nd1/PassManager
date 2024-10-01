package com.vondi.passmanager.presentation.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import com.vondi.passmanager.ui.theme.LightGreen


sealed class Category(
    val name: String,
    val selected: Boolean
) {
    companion object {
        fun allCategories(): List<Category> {
            return listOf(
                Sites,
                Accounts,
                Browsers,
                Games,
                Work,
                Ide,
                Group
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


    data object Games: Category(
        name = "Games",
        selected = false
    )

    data object Ide: Category(
        name = "Ide",
        selected = false
    )

    data object Group: Category(
        name = "Group",
        selected = false
    )

    data object Work: Category(
        name = "Work",
        selected = false
    )

}

@Composable
private fun CategoryButton(
    category: Category
) {
    Box(
        modifier = Modifier
            .padding(Dimens.Small)
            .wrapContentWidth()
            .clip(RoundedCornerShape(50))
            .background(Tertiary)

    ) {
        Text(
            text = category.name,
            color = TextColor,
            fontSize = FontSize.SmallMedium,
            modifier = Modifier
                .padding(Dimens.Small)
        )
    }
}

@Composable
fun CategoryList(
    categories: List<Category> = Category.allCategories()
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            Spacer(Modifier.width(Dimens.Small))
        }
        items(categories) {
            CategoryButton(it)
        }
    }

}