package com.vondi.passmanager.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.passmanager.domain.model.Item
import com.vondi.passmanager.data.dao.ItemDao
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.ItemState
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
            ItemEvent.HideDialogAdd -> {
                _state.update { it.copy(
                    isAddingItem = false
                ) }
            }
            ItemEvent.HideDialogChange -> {
                _state.update { it.copy(
                    isChangeItem = false
                ) }
            }
            ItemEvent.SaveItem -> {
                val url = state.value.url
                val login = state.value.login
                val password = state.value.password
                val name = state.value.name

                if(url.isBlank() || login.isBlank() || password.isBlank() || name.isBlank()){
                    return
                }

                val item = Item(
                    url = url,
                    password = password,
                    login = login,
                    name = name
                )

                viewModelScope.launch {
                    dao.upsertItem(item)
                }

                _state.update { it.copy(
                    isAddingItem = false,
                    url = "",
                    login = "",
                    password = "",
                    name = ""
                ) }
            }
            ItemEvent.SaveChangedItem -> {
                val id = state.value.id
                val url = state.value.url
                val login = state.value.login
                val password = state.value.password
                val name = state.value.name

                val item = Item(
                    id = id,
                    url = url,
                    password = password,
                    login = login,
                    name = name
                )

                viewModelScope.launch {
                    dao.updateItem(item)
                }

                _state.update { it.copy(
                    isChangeItem = false,
                    url = "",
                    login = "",
                    password = "",
                    name = ""
                ) }
            }
            is ItemEvent.SetLogin -> {
                _state.update {it.copy(
                    login = event.login
                )}
            }
            is ItemEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is ItemEvent.SetPassword -> {
                _state.update {it.copy(
                    password = event.password
                )}
            }
            is ItemEvent.SetUrl -> {
                _state.update {it.copy(
                    url = event.url
                )}
            }
            ItemEvent.ShowDialogAdd -> {
                _state.update { it.copy(
                    isAddingItem = true
                ) }
            }
            ItemEvent.ShowDialogChange-> {
                _state.update { it.copy(
                    isChangeItem = true
                ) }
            }
            is ItemEvent.EditItem -> {
                _state.update { it.copy(
                    editingItem = event.item
                ) }
            }

        }
    }

}