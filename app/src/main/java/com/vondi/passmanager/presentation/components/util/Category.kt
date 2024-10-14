package com.vondi.passmanager.presentation.components.util

sealed class Category(
    open val name: String
) {

    data object All : Category(name = "Все")
    data class Specific(override val name: String) : Category(name)

}