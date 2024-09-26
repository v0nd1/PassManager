package com.vondi.passmanager.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String,
    val login: String,
    val password: String,
    val logoUrl: String = ""
){
    companion object {
        fun emptyItem() : Item {
            return Item(0, "", "", "", "", "")
        }
    }
}
