package com.vondi.passmanager

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Item(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val url: String,
    val login: String,
    val password: String,
)
