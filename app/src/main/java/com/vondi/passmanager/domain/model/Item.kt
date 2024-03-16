package com.vondi.passmanager.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Item(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val url: String,
    val login: String,
    val password: String,
)
