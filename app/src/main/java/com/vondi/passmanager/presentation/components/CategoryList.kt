package com.vondi.passmanager.presentation.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CategoryList(

) {
    var expanded by remember { mutableStateOf(false) }
    DropdownMenu(expanded = expanded, onDismissRequest = { /*TODO*/ }) {

    }
}