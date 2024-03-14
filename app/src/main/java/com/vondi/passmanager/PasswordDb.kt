package com.vondi.passmanager

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],
    version = 1
)
abstract class PasswordDb: RoomDatabase() {

    abstract val dao: ItemDao

}