package com.vondi.passmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.components.CategoryList
import com.vondi.passmanager.presentation.components.ItemCard
import com.vondi.passmanager.presentation.components.PassNavBar
import com.vondi.passmanager.presentation.components.PassSearchBar
import com.vondi.passmanager.presentation.screens.dialogs.AddItemDialog
import com.vondi.passmanager.presentation.screens.dialogs.ChangeItemDialog
import com.vondi.passmanager.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    stateItem: ItemState,
    onEvent: (ItemEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { onEvent(ItemEvent.ShowDialogAdd) },
//                containerColor = MaterialTheme.colorScheme.tertiary,
//                contentColor = White
//            ) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add))
//            }
        },
        bottomBar = {
            PassNavBar()
        },
        topBar = {
            PassSearchBar()
        }

    ) {

        if (stateItem.isAddingItem) {
            AddItemDialog(
                state = stateItem,
                onEvent = onEvent
            )
        }
        if (stateItem.editingItem != null && stateItem.isChangeItem) {
            ChangeItemDialog(
                state = stateItem,
                onEvent = onEvent,
                item = stateItem.editingItem
            )
        }
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CategoryList()
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (stateItem.items.isEmpty()) {
                        //item { Text(text = stringResource(R.string.noone_passwords)) }
                    }
                    items(stateItem.items) { item ->
                        ItemCard(
                            item = item,
                            onDelete = { onEvent(ItemEvent.DeleteItem(item)) },
                            onClick = { onEvent(ItemEvent.ShowDialogChange(item)) },
                            onSelect = { selected -> onEvent(ItemEvent.EditItem(selected)) }
                        )
                    }
                }
            }

        }


    }
}