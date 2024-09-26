package com.vondi.passmanager.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState

@Composable
fun ChangeItemScreen(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit,
    navController: NavController
) {

}