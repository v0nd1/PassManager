package com.vondi.passmanager.domain.model.item

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Tertiary
import com.vondi.passmanager.presentation.components.util.Category
import com.vondi.passmanager.ui.theme.LightGreen
import com.vondi.passmanager.ui.theme.Red

data class ItemState(
    val items: List<Item> = emptyList(),
    val item: Item = Item.emptyItem(),
    val categories: List<Category> = listOf(Category.All),
    val currentCategory: Category = Category.All,
    val currentColor: Color = LightGreen,
    val color: Int = Red.toArgb(),
    val id: Int = 0,
    val url: String = "",
    val logoUrl: String = "",
    val name: String = "",
    val login: String = "",
    val password: String = "",
    val category: String = "",
    val isAddingItem: Boolean = false,
    val isChangeItem: Boolean = false,
    val editingItem: Item? = null
)