package com.vondi.passmanager.presentation.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.item.ItemState
import com.vondi.passmanager.presentation.screens.AddItemScreen
import com.vondi.passmanager.presentation.screens.ChangeItemScreen
import com.vondi.passmanager.presentation.screens.ItemScreen
import com.vondi.passmanager.presentation.screens.PinLockScreen
import com.vondi.passmanager.presentation.viewmodels.ItemViewModel
import com.vondi.passmanager.presentation.viewmodels.PinLockViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    pinLockViewModel: PinLockViewModel,
    onEvent: (ItemEvent) -> Unit,
    state: ItemState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(route = Screen.Auth.route) {
            PinLockScreen(
                navController = navController,
                viewModel = pinLockViewModel
            )
        }

        composable(route = Screen.Item.route) {
            ItemScreen(
                stateItem = state,
                onEvent = onEvent
            )
        }

        composable(route = Screen.AddItem.route) {
            AddItemScreen(
                state = state,
                onEvent = onEvent,
                navController = navController
            )
        }

        composable(route = Screen.ChangeItem.route) {
            ChangeItemScreen(
                state = state,
                onEvent = onEvent,
                navController = navController
            )
        }

    }
}