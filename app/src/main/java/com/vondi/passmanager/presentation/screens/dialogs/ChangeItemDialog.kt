package com.vondi.passmanager.presentation.screens.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.domain.model.item.ItemState

@Composable
fun ChangeItemDialog(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier,
    item: Item
) {
    onEvent(ItemEvent.SetId(item.id))
    onEvent(ItemEvent.SetItem(item))
    AlertDialog(
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(ItemEvent.SaveChangedItem)

                }) {
                    Text(text = "Сохранить")
                }
            }
        },
        modifier = modifier,
        onDismissRequest = {
            onEvent(ItemEvent.HideDialogChange)
        },
        title = { Text(text = "Изменить") },
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
                            Icon(
                                painter = painterResource(id = R.drawable.clipboard),
                                contentDescription = "Скопировать",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    maxLines = 1
                )
                TextField(
                    value = state.url,
                    onValueChange = {
                        onEvent(ItemEvent.SetUrl(it))
                    },
                    placeholder = {
                        Text(text = item.url)
                    },
                    trailingIcon = {
                        IconButton(onClick = { clipboardManager.setText(AnnotatedString(item.url)) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.clipboard),
                                contentDescription = "Скопировать",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    maxLines = 1
                )
                TextField(
                    value = state.login,
                    onValueChange = {
                        onEvent(ItemEvent.SetLogin(it))
                    },
                    placeholder = {
                        Text(text = item.login)
                    },
                    trailingIcon = {
                        IconButton(onClick = { clipboardManager.setText(AnnotatedString(item.login)) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.clipboard),
                                contentDescription = "Скопировать",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    maxLines = 1
                )
                TextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = {
                        Text(text = item.password)
                    },
                    trailingIcon = {
                        IconButton(onClick = { clipboardManager.setText(AnnotatedString(item.password)) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.clipboard),
                                contentDescription = "Скопировать",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    maxLines = 1
                )
            }
        }
    )
}
