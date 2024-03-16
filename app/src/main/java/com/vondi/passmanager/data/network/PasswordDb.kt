package com.vondi.passmanager.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vondi.passmanager.domain.model.Item
import com.vondi.passmanager.data.dao.ItemDao

@Database(
    entities = [Item::class],
    version = 1
)
abstract class PasswordDb: RoomDatabase() {

    abstract val dao: ItemDao

}