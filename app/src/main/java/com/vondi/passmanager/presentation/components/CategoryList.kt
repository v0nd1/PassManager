package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import com.vondi.passmanager.presentation.common.Container
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.Tertiary
import com.vondi.passmanager.presentation.common.TextColor
import com.vondi.passmanager.presentation.components.util.Category


@Composable
private fun CategoryButton(
    category: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .padding(Dimens.Small)
            .wrapContentWidth()
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) Container else Tertiary)
            .clickable(
                enabled = !isSelected,
                onClick = { if (!isSelected) onClick() }
            )

    ) {
        Text(
            text = category,
            color = if (isSelected) Gray else TextColor,
            fontSize = FontSize.SmallMedium,
            modifier = Modifier
                .padding(horizontal = Dimens.Small, vertical = Dimens.ExtraSmall)
        )
    }
}

@Composable
fun CategoryList(
    categories: List<Category>,
    currentCategory: Category,
    onClick: (Category) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            Spacer(Modifier.width(Dimens.Small))
        }
        items(categories) { category ->
            CategoryButton(
                category = category.name,
                isSelected = category == currentCategory,
                onClick = { onClick(category) }
            )
        }
    }
}