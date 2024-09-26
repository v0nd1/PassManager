package com.vondi.passmanager.domain.model.item

import com.vondi.passmanager.data.models.Item

data class ItemState(
    val items: List<Item> = emptyList(),
    val item: Item = Item.emptyItem(),
    val id: Int = 0,
    val url: String = "",
    val logoUrl: String = "",
    val name: String = "",
    val login: String = "",
    val password: String = "",
    val isAddingItem: Boolean = false,
    val isChangeItem: Boolean = false,
    val editingItem: Item? = null
)