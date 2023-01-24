package com.betulnecanli.sailormoonapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betulnecanli.sailormoonapp.data.local.SailorMoonDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : RoomDatabase{
        return Room.databaseBuilder(
            context,
            SailorMoonDB::class.java,
            "sailormoonDB"
        ).build()
    }


    @Singleton
    @Provides
    fun provideCharacterDao(db : SailorMoonDB) = db.sailorMoonDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(db : SailorMoonDB) = db.remoteKeysDao()





}