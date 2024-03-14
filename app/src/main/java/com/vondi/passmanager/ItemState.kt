package com.vondi.passmanager

data class ItemState(
    val items: List<Item> = emptyList(),
    val url: String = "",
    val login: String = "",
    val password: String = "",
    val isAddingItem: Boolean = false
)