package com.vondi.passmanager.data.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.data.dao.ItemDao

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
abstract class PasswordDb : RoomDatabase() {

    abstract val dao: ItemDao

}