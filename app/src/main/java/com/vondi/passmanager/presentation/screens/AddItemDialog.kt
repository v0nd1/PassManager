package com.vondi.passmanager.presentation.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.ItemState



@Composable
fun AddItemDialog(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    AlertDialog(
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    if(state.url.isBlank() ||state.name.isBlank() || state.password.isBlank() || state.login.isBlank()){
                        Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
                    }else{
                        onEvent(ItemEvent.SaveItem)
                    }
                }) {
                    Text(text = "Save")
                }
            }
        },
        modifier = modifier,
        onDismissRequest = {
            onEvent(ItemEvent.HideDialogAdd)
        },
        title = { Text(text = "Add item") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(ItemEvent.SetName(it))
                    },
                    placeholder = {
                        Text(text = "Name")
                    }
                )
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