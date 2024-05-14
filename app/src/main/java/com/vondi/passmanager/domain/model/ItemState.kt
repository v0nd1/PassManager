package com.vondi.passmanager.domain.model

data class ItemState(
    val items: List<Item> = emptyList(),
    val item: Item ?= null,
    val id: Int = 0,
    val url: String = "",
    val name: String = "",
    val login: String = "",
    val password: String = "",
    val isAddingItem: Boolean = false,
    val isChangeItem: Boolean = false,
    val editingItem: Item? = null
)