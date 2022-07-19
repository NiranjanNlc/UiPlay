package com.example.playwithui.di

import android.content.Context
import androidx.room.Room
import com.example.playwithui.modal.database.SongDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

            @Provides
            @Singleton
            fun getDatabase( @ApplicationContext context: Context)= Room.databaseBuilder(
                    context.applicationContext,
                    SongDataBase::class.java,
                    "song_database"
                ).build()

            @Provides
            @Singleton
            fun getDao(dataBase: SongDataBase)= dataBase.songDao() 
            }
