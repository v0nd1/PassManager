package com.vondi.passmanager.domain.model

import com.vondi.passmanager.domain.model.Item

data class ItemState(
    val items: List<Item> = emptyList(),
    val url: String = "",
    val name: String = "",
    val login: String = "",
    val password: String = "",
    val isAddingItem: Boolean = false
)