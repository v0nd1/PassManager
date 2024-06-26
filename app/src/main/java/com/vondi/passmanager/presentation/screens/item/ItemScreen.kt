package com.vondi.passmanager.presentation.screens.item

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.components.ItemCard
import com.vondi.passmanager.presentation.screens.dialogs.AddItemDialog
import com.vondi.passmanager.presentation.screens.dialogs.ChangeItemDialog
import com.vondi.passmanager.ui.theme.DarkGreen
import com.vondi.passmanager.ui.theme.Green
import com.vondi.passmanager.ui.theme.White

@Composable
fun ItemScreen(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(ItemEvent.ShowDialogAdd) },
                containerColor = if (isSystemInDarkTheme()) Green else DarkGreen,
                contentColor = White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить")
            }
        }

    ) {
        if (state.isAddingItem) {
            AddItemDialog(
                state = state,
                onEvent = onEvent
            )
        }
        if (state.editingItem != null && state.isChangeItem) {
            ChangeItemDialog(
                state = state,
                onEvent = onEvent,
                item = state.editingItem
            )
        }
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.items.isEmpty()) {
                item { Text(text = "Пароли отсутствуют") }
            }
            items(state.items) { item ->
                ItemCard(
                    item = item,
                    onDelete = { onEvent(ItemEvent.DeleteItem(item)) },
                    onClick = { onEvent(ItemEvent.ShowDialogChange) },
                    onSelect = { selected -> onEvent(ItemEvent.EditItem(selected)) }
                )
            }
        }
    }
}