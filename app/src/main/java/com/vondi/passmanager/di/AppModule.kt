package com.vondi.passmanager.di

import android.content.Context
import androidx.room.Room
import com.vondi.passmanager.data.dao.ItemDao
import com.vondi.passmanager.data.network.PasswordDb
import com.vondi.passmanager.data.util.KeystoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDb(@ApplicationContext context: Context) : PasswordDb = Room.databaseBuilder(
        context, PasswordDb::class.java, "passwords.db"
    ).build()

    @Provides
    fun provideDao(db: PasswordDb): ItemDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun providesKeystoreManager(@ApplicationContext context: Context): KeystoreManager {
        return KeystoreManager(context)
    }

}