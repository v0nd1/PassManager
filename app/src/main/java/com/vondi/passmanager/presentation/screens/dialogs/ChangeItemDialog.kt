package com.vondi.passmanager.presentation.screens.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.PrimaryColor
import com.vondi.passmanager.presentation.common.Shape
import com.vondi.passmanager.presentation.common.TextColor
import com.vondi.passmanager.presentation.components.CategoryField
import com.vondi.passmanager.presentation.components.PassCopyTextField
import com.vondi.passmanager.presentation.components.PassTextField
import com.vondi.passmanager.presentation.components.util.Category

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
                Button(
                    onClick = {
                        onEvent(ItemEvent.SaveChangedItem)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,
                        contentColor = TextColor
                    ),
                    shape = RoundedCornerShape(Shape.Small)
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        color = TextColor
                    )
                }
            }
        },
        modifier = modifier,
        onDismissRequest = {
            onEvent(ItemEvent.HideDialogChange)
        },
        title = { Text(text = stringResource(R.string.change)) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val clipboardManager = LocalClipboardManager.current

                PassCopyTextField(
                    value = state.name,
                    onValueChange = {
                        it.filter {
                            it.isLetterOrDigit()
                        }
                        onEvent(ItemEvent.SetName(it))
                    },
                    placeholder = item.name
                ){
                    clipboardManager.setText(AnnotatedString(item.name))
                }

                PassCopyTextField(
                    value = state.url,
                    onValueChange = {
                        it.filter {
                            it.isLetterOrDigit()
                        }
                        onEvent(ItemEvent.SetUrl(it))
                    },
                    placeholder = item.url
                ){
                    clipboardManager.setText(AnnotatedString(item.url))
                }

                PassCopyTextField(
                    value = state.login,
                    onValueChange = {
                        it.filter {
                            it.isLetterOrDigit()
                        }
                        onEvent(ItemEvent.SetLogin(it))
                    },
                    placeholder = item.login
                ){
                    clipboardManager.setText(AnnotatedString(item.login))
                }

                PassCopyTextField(
                    value = state.password,
                    onValueChange = {
                        it.filter {
                            it.isLetterOrDigit()
                        }
                        onEvent(ItemEvent.SetPassword(it))
                    },
                    placeholder = item.password
                ){
                    clipboardManager.setText(AnnotatedString(item.password))
                }

                Column {
                    var expanded by remember { mutableStateOf(false) }
                    var categoryFieldWidth by remember { mutableIntStateOf(0) }
                    var categoryFieldHeight by remember { mutableIntStateOf(0) }
                    CategoryField(
                        value = state.category,
                        onValueChange = {
                            it.filter {
                                it.isLetterOrDigit()
                            }
                            onEvent(ItemEvent.SetCategory(it))
                        },
                        placeholder = item.category,
                        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                            categoryFieldWidth = layoutCoordinates.size.width
                            categoryFieldHeight = layoutCoordinates.size.height
                        }
                    ) {
                        expanded = true
                    }

                    DropdownMenu(
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(with(LocalDensity.current) { categoryFieldWidth.toDp() })
                            .heightIn(max = with(LocalDensity.current) { categoryFieldHeight.toDp() * 3 }),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        state.categories.filter { it.name != Category.All.name }
                            .forEach { category ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = category.name,
                                            fontSize = FontSize.Small,
                                            fontWeight = FontWeight.Medium
                                        )
                                    },
                                    onClick = {
                                        onEvent(ItemEvent.SetCategory(category.name))
                                        expanded = false
                                    }
                                )
                            }
                    }
                }
//                PassCopyTextField(
//                    value = state.category,
//                    onValueChange = {
//                        it.filter {
//                            it.isLetterOrDigit()
//                        }
//                        onEvent(ItemEvent.SetCategory(it))
//                    },
//                    placeholder = item.category
//                ){
//                    clipboardManager.setText(AnnotatedString(item.category))
//                }


            }
        }
    )
}


