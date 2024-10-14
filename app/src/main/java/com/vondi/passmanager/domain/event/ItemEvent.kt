package com.vondi.passmanager.domain.event

import androidx.compose.ui.graphics.Color
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.presentation.components.util.Category

sealed interface ItemEvent {

    data object SaveItem : ItemEvent
    data object SaveChangedItem : ItemEvent
    data object ShowDialogAdd : ItemEvent
    data class ShowDialogChange(val item: Item) : ItemEvent
    data object HideDialogAdd : ItemEvent
    data object HideDialogChange : ItemEvent

    data class SetItem(val item: Item) : ItemEvent
    data class SetCurCategory(val currentCategory: Category) : ItemEvent
    data class SetCurColor(val currentColor: Color) : ItemEvent
    data class SetUrl(val url: String) : ItemEvent
    data class SetId(val id: Int) : ItemEvent
    data class SetName(val name: String) : ItemEvent
    data class SetLogin(val login: String) : ItemEvent
    data class SetPassword(val password: String) : ItemEvent
    data class SetCategory(val category: String) : ItemEvent
    data class DeleteItem(val item: Item) : ItemEvent
    data class EditItem(val item: Item) : ItemEvent
    data class UpdateSearchQuery(val query: String) : ItemEvent
    data class SetColor(val color: Int) : ItemEvent

}