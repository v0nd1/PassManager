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
                onEvent(ItemEvent.ShowDialog)
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
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            items(state.items){
                //ItemCard(item = it, onClick = { onEvent(ItemEvent.DeleteItem(it))} )
//                Row(
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
//                ) {
//                    Column(
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Text(text = it.login, fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text(text = it.url, fontSize = 14.sp)
//                        Text(text = it.password, fontSize = 16.sp)
//                    }
//                    IconButton(onClick = {
//                        onEvent(ItemEvent.DeleteItem(it))
//                    }) {
//                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete item")
//                    }
//
//                }
            }

        }

    }

}