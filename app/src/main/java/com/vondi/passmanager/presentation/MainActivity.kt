package com.vondi.passmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vondi.passmanager.presentation.navigation.NavGraph
import com.vondi.passmanager.presentation.viewmodels.ItemViewModel
import com.vondi.passmanager.presentation.viewmodels.PinLockViewModel
import com.vondi.passmanager.ui.theme.PassManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModelItem: ItemViewModel by viewModels()
    private val pinLockViewModel: PinLockViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PassManagerTheme {
                val state by viewModelItem.state.collectAsState()
                NavGraph(
                    pinLockViewModel = pinLockViewModel,
                    onEvent = viewModelItem::onEvent,
                    state = state
                )

            }
        }
    }
}
