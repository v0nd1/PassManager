package com.vondi.passmanager

sealed interface ItemEvent {

    data object SaveItem: ItemEvent
    data object ShowDialog: ItemEvent
    data object HideDialog: ItemEvent

    data class SetUrl(val url: String): ItemEvent
    data class SetLogin(val login: String): ItemEvent
    data class SetPassword(val password: String): ItemEvent
    data class DeleteItem(val item: Item): ItemEvent

}