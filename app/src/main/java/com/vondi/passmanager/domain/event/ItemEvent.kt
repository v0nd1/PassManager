package com.vondi.passmanager.domain.event

import com.vondi.passmanager.data.models.Item

sealed interface ItemEvent {

    data object SaveItem : ItemEvent
    data object SaveChangedItem : ItemEvent
    data object ShowDialogAdd : ItemEvent
    data class ShowDialogChange(val item: Item) : ItemEvent
    data object HideDialogAdd : ItemEvent
    data object HideDialogChange : ItemEvent

    data class SetItem(val item: Item) : ItemEvent
    data class SetUrl(val url: String) : ItemEvent
    data class SetId(val id: Int) : ItemEvent
    data class SetName(val name: String) : ItemEvent
    data class SetLogin(val login: String) : ItemEvent
    data class SetPassword(val password: String) : ItemEvent
    data class DeleteItem(val item: Item) : ItemEvent
    data class EditItem(val item: Item) : ItemEvent

}