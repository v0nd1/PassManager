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

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Item ADD COLUMN logoUrl TEXT DEFAULT '' NOT NULL")
            }
        }

        fun getInstance(context: Context): PasswordDb {
            return Room.databaseBuilder(
                context.applicationContext,
                PasswordDb::class.java,
                "password_db"
            ).addMigrations(MIGRATION_1_2).build()
        }
    }
}