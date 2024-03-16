package com.vondi.passmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.ItemState

@Composable
fun AddItemDialog(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(ItemEvent.SaveItem)
                }) {
                    Text(text = "Save")
                }
            }
        },
        modifier = modifier,
        onDismissRequest = {
            onEvent(ItemEvent.HideDialog)
        },
        title = { Text(text = "Add contact") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.url,
                    onValueChange = {
                        onEvent(ItemEvent.SetUrl(it))
                    },
                    placeholder = {
                        Text(text = "URL")
                    }
                )
                TextField(
                    value = state.login,
                    onValueChange = {
                        onEvent(ItemEvent.SetLogin(it))
                    },
                    placeholder = {
                        Text(text = "Login")
                    }
                )
                TextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = {
                        Text(text = "Password")
                    }
                )
            }
        }
    )

}