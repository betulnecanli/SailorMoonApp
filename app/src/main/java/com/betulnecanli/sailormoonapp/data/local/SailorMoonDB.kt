package com.betulnecanli.sailormoonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.betulnecanli.sailormoonapp.data.local.entity.RemoteKeysDAO
import com.betulnecanli.sailormoonapp.data.local.entity.RemoteKeysEntity
import com.betulnecanli.sailormoonapp.data.local.entity.SailorMoonEntity
import com.betulnecanli.sailormoonapp.utils.DatabaseConverter


@Database(entities = [SailorMoonEntity::class, RemoteKeysEntity::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class SailorMoonDB : RoomDatabase(){
    abstract fun sailorMoonDao() : SailorMoonDAO
    abstract fun remoteKeysDao() : RemoteKeysDAO
}