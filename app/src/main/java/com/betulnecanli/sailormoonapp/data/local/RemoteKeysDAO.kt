package com.betulnecanli.sailormoonapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.betulnecanli.sailormoonapp.data.local.entities.RemoteKeysEntity

@Dao
interface RemoteKeysDAO {

    @Query("SELECT * FROM remoteKeys WHERE id = :id")
    suspend fun getRemoteKey(id : Int): RemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKey: List<RemoteKeysEntity>)

    @Query("DELETE FROM remoteKeys")
    suspend fun deleteAllRemoteKeys()

}