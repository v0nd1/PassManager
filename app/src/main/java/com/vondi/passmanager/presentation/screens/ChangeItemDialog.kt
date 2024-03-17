package com.vondi.passmanager.presentation.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.Item
import com.vondi.passmanager.domain.model.ItemState

@Composable
fun ChangeItemDialog(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier,
    item: Item
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
            onEvent(ItemEvent.HideDialogChange)
        },
        title = { Text(text = "Change item") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val clipboardManager = LocalClipboardManager.current
                TextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(ItemEvent.SetName(it))
                    },
                    placeholder = {
                        Text(text = item.name)
                    },
                    trailingIcon = {
                        IconButton(onClick = { clipboardManager.setText(AnnotatedString(item.name)) }) {
                            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "dd")
                        }
                    }
                )
                TextField(
                    value = state.url,
                    onValueChange = {
                        onEvent(ItemEvent.SetUrl(it))
                    },
                    placeholder = {
                        Text(text = item.url)
                    }
                )
                TextField(
                    value = state.login,
                    onValueChange = {
                        onEvent(ItemEvent.SetLogin(it))
                    },
                    placeholder = {
                        Text(text = item.login)
                    }
                )
                TextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = {
                        Text(text = item.password)
                    }
                )
            }
        }
    )

}
