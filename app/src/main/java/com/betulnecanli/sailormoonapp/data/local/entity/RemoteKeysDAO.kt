package com.betulnecanli.sailormoonapp.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDAO {

    @Query("SELECT * FROM remoteKeys WHERE id = :id")
    suspend fun getRemoteKey(id : Int): RemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeysEntity: List<RemoteKeysEntity>)

    @Query("DELETE FROM remoteKeys")
    suspend fun deleteAllRemoteKeys()

}