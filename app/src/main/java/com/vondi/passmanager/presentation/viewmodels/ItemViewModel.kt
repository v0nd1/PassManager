package com.vondi.passmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.passmanager.data.dao.ItemDao
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.data.util.getFaviconUrl
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.components.util.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor (
    private val dao: ItemDao
) : ViewModel() {

    private val _state = MutableStateFlow(ItemState())
    private val _categories = dao.getCategories()
        .map { categories -> listOf(Category.All) + categories.map { Category.Specific(it) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _searchQuery = MutableStateFlow("")
    @OptIn(FlowPreview::class)
    private val searchQueryFlow = _searchQuery
        .debounce(300)
        .distinctUntilChanged()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _items = _state
        .flatMapLatest { state ->
            if (state.currentCategory == Category.All) {
                dao.getItems()
            } else {
                dao.getItemsByCategory((state.currentCategory as Category.Specific).name)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _items, _categories, searchQueryFlow) { state, items, categories, query ->
        if (query.isBlank()) {
            state.copy(
                items = items,
                categories = categories
            )
        } else {
            state.copy(
                items =  items.filter { it.name.lowercase().contains(query.lowercase(), ignoreCase = true) },
                categories = categories
            )
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemState())


    fun onEvent(
        event: ItemEvent
    ) {
        when (event) {

            is ItemEvent.DeleteItem -> {
                viewModelScope.launch {
                    dao.deleteItem(event.item)
                }
            }

            ItemEvent.HideDialogAdd -> {
                _state.update {
                    it.copy(
                        isAddingItem = false
                    )
                }
            }

            ItemEvent.HideDialogChange -> {
                _state.update {
                    it.copy(
                        isChangeItem = false
                    )
                }
            }

            ItemEvent.SaveItem -> {
                viewModelScope.launch {
                    val url = state.value.url
                    val login = state.value.login
                    val password = state.value.password
                    val name = state.value.name
                    val logoUrl = getFaviconUrl(url)
                    val category = state.value.category
                    val color = state.value.color

                    if (url.isBlank() || login.isBlank() || password.isBlank() || name.isBlank() || category.isBlank()) {
                        return@launch
                    }

                    val item = Item(
                        url = url,
                        password = password,
                        login = login,
                        name = name,
                        logoUrl = logoUrl,
                        category = category,
                        color = color
                    )

                    dao.upsertItem(item)

                    _state.update {
                        it.copy(
                            isAddingItem = false,
                            url = "",
                            login = "",
                            password = "",
                            name = "",
                            logoUrl = "",
                            category = ""
                        )
                    }
                }
            }

            ItemEvent.SaveChangedItem -> {
                viewModelScope.launch {  val id = state.value.item.id
                    val url = state.value.url
                    val login = state.value.login
                    val password = state.value.password
                    val name = state.value.name
                    val logoUrl = getFaviconUrl(url)
                    val category = state.value.category

                    val currentItem = state.value.item

                    val newItem = currentItem.copy(
                        id = id,
                        url = url.ifBlank { currentItem.url },
                        login = login.ifBlank { currentItem.login },
                        password = password.ifBlank { currentItem.password },
                        name = name.ifBlank { currentItem.name },
                        logoUrl = logoUrl.ifBlank { currentItem.logoUrl },
                        category = category.ifBlank { currentItem.category }
                    )

                    newItem.let { dao.updateItem(it) }

                    _state.update {
                        it.copy(
                            isChangeItem = false,
                            url = if (url.isNotBlank()) "" else it.url,
                            login = if (login.isNotBlank()) "" else it.login,
                            password = if (password.isNotBlank()) "" else it.password,
                            name = if (name.isNotBlank()) "" else it.name,
                            logoUrl = if (logoUrl.isNotBlank()) "" else it.logoUrl,
                            category = if (category.isNotBlank()) "" else it.category,

                        )
                    } }

            }

            is ItemEvent.SetItem -> {
                _state.update {
                    it.copy(
                        item = event.item
                    )
                }
            }

            is ItemEvent.SetCurCategory -> {
                _state.update {
                    it.copy(
                        currentCategory = event.currentCategory
                    )
                }
            }

            is ItemEvent.SetCurColor -> {
                _state.update {
                    it.copy(
                        currentColor = event.currentColor
                    )
                }
            }

            is ItemEvent.SetLogin -> {
                _state.update {
                    it.copy(
                        login = event.login
                    )
                }
            }

            is ItemEvent.SetName -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is ItemEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is ItemEvent.SetCategory -> {
                _state.update {
                    it.copy(
                        category = event.category
                    )
                }
            }

            is ItemEvent.SetUrl -> {
                _state.update {
                    it.copy(
                        url = event.url
                    )
                }
            }

            is ItemEvent.SetId -> {
                _state.update {
                    it.copy(
                        id = event.id
                    )
                }
            }

            ItemEvent.ShowDialogAdd -> {
                _state.update {
                    it.copy(
                        isAddingItem = true
                    )
                }
            }

            is ItemEvent.ShowDialogChange -> {
                _state.update {
                    it.copy(
                        isChangeItem = true,
                        item = event.item
                    )
                }
            }

            is ItemEvent.EditItem -> {
                _state.update {
                    it.copy(
                        editingItem = event.item
                    )
                }
            }

            is ItemEvent.UpdateSearchQuery -> {
                _searchQuery.value = event.query
            }

            is ItemEvent.SetColor -> {
                _state.update {
                    it.copy(
                        color = event.color
                    )
                }
            }


        }
    }

}