package com.vondi.passmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.vondi.passmanager.data.network.PasswordDb
import com.vondi.passmanager.data.util.KeystoreManager
import com.vondi.passmanager.presentation.screens.item.ItemScreen
import com.vondi.passmanager.presentation.screens.item.ItemViewModel
import com.vondi.passmanager.presentation.screens.pinlock.PinLockScreen
import com.vondi.passmanager.presentation.screens.pinlock.PinLockViewModel
import com.vondi.passmanager.ui.theme.PassManagerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModelItem: ItemViewModel by viewModels()
    private val pinLockViewModel: PinLockViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PassManagerTheme {
                val state by viewModelItem.state.collectAsState()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth") {
                    composable("auth") {
                        PinLockScreen(
                            navController = navController,
                            viewModel = pinLockViewModel
                        )
                    }
                    composable("mainScreen") {
                        ItemScreen(stateItem = state, onEvent = viewModelItem::onEvent)
                    }

                }

            }
        }
    }
}
