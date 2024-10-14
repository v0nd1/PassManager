package com.vondi.passmanager.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.vondi.passmanager.data.models.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Upsert
    suspend fun upsertItem(item: Item)

    @Upsert
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * FROM item WHERE category = :category ORDER BY category")
    fun getItemsByCategory(category: String): Flow<List<Item>>

    @Query("SELECT DISTINCT category FROM Item")
    fun getCategories(): Flow<List<String>>

}