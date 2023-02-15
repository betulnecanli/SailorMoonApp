package com.betulnecanli.sailormoonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.betulnecanli.sailormoonapp.data.local.entities.RemoteKeysEntity
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.utils.DatabaseConverter


@Database(entities = [SailorMoon::class, RemoteKeysEntity::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class SailorMoonDB : RoomDatabase(){
    abstract fun sailorMoonDao() : SailorMoonDAO
    abstract fun remoteKeysDao() : RemoteKeysDAO
}