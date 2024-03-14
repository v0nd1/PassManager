package com.vondi.passmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemViewModel(
    private val dao: ItemDao
): ViewModel() {

    private val _state = MutableStateFlow(ItemState())
    private val _items = dao.getItems().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _items){ state, items ->
        state.copy(
            items = items
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemState())

    fun onEvent(
        event: ItemEvent
    ){
        when(event){
            is ItemEvent.DeleteItem -> {
                viewModelScope.launch {
                    dao.deleteItem(event.item)
                }

            }
            ItemEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingItem = false
                ) }
            }
            ItemEvent.SaveItem -> {
                val url = state.value.url
                val login = state.value.login
                val password = state.value.password

                if(url.isBlank() || login.isBlank() || password.isBlank()){
                    return
                }

                val item = Item(
                    url = url,
                    password = password,
                    login = login
                )

                viewModelScope.launch {
                    dao.upsertItem(item)
                }

                _state.update { it.copy(
                    isAddingItem = false,
                    url = "",
                    login = "",
                    password = ""
                ) }
            }
            is ItemEvent.SetLogin -> {
                _state.update {it.copy(
                    login = event.login
                )}
            }
            is ItemEvent.SetPassword -> {
                _state.update {it.copy(
                    login = event.password
                )}
            }
            is ItemEvent.SetUrl -> {
                _state.update {it.copy(
                    login = event.url
                )}
            }
            ItemEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingItem = true
                ) }
            }
        }
    }

}