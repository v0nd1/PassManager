package com.vondi.passmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.ItemState
import com.vondi.passmanager.presentation.components.ItemCard

@Composable
fun ItemScreen(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ItemEvent.ShowDialogAdd)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
            }
        }

    ) {
        if (state.isAddingItem){
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
            modifier = Modifier.fillMaxSize()
        ){
            items(state.items){item->
                ItemCard(
                    item = item,
                    onDelete = { onEvent(ItemEvent.DeleteItem(item))},
                    onClick = {onEvent(ItemEvent.ShowDialogChange)},
                    onSelect = { selected-> onEvent(ItemEvent.EditItem(selected))}
                )

            }


        }


    }

}