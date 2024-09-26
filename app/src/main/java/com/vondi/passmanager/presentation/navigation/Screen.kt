package com.vondi.passmanager.presentation.navigation


sealed class Screen(
    val route: String
) {

    data object Auth : Screen(
        route = "auth"
    )

    data object Item : Screen(
        route = "item"
    )

    data object ChangeItem : Screen(
        route = "change_item"
    )

    data object AddItem : Screen(
        route = "add_item"
    )



}