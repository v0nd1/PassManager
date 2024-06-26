package com.vondi.passmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.vondi.passmanager.data.network.PasswordDb
import com.vondi.passmanager.presentation.screens.item.ItemScreen
import com.vondi.passmanager.presentation.screens.item.ItemViewModel
import com.vondi.passmanager.presentation.screens.pinlock.PinLockScreen
import com.vondi.passmanager.ui.theme.PassManagerTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PasswordDb::class.java,
            "password.db"
        ).build()
    }

    private val viewModelItem by viewModels<ItemViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ItemViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PassManagerTheme {
                val state by viewModelItem.state.collectAsState()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth") {
                    composable("auth") {
                        PinLockScreen(navController = navController)
                    }
                    composable("mainScreen") {
                        ItemScreen(state = state, onEvent = viewModelItem::onEvent)
                    }

                }

            }
        }
    }
}
