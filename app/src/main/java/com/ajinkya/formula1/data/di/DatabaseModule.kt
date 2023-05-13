package com.ajinkya.formula1.data.di

import android.content.Context
import androidx.room.Room
import com.ajinkya.formula1.core.common.Constant
import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.database.F1Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        F1Database::class.java,
        com.ajinkya.formula1.core.common.Constant.F1_DATABASE
    ).build()


    @Singleton
    @Provides
    fun provideDao(
        db: F1Database
    ): F1Dao {
        return db.f1Dao()
    }
}