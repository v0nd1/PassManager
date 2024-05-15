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
import com.vondi.passmanager.presentation.screens.ItemScreen
import com.vondi.passmanager.presentation.screens.ItemViewModel
import com.vondi.passmanager.presentation.screens.PasswordScreen
import com.vondi.passmanager.ui.theme.PassManagerTheme
import xyz.teamgravity.pin_lock_compose.PinManager

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PasswordDb::class.java,
            "password.db"
        ).build()
    }

    private val viewModel by viewModels<ItemViewModel> (
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
        PinManager.initialize(this)
        setContent {
            PassManagerTheme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth"){
                    composable("auth"){
                        PasswordScreen(navController)
                    }
                    composable("mainScreen"){
                        ItemScreen(state = state, onEvent = viewModel::onEvent)
                    }

                }

            }
        }
    }
}
