package com.betulnecanli.sailormoonapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants


@Entity(tableName = Constants.REMOTEKEY_TABLE)
data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val prevPage : Int?,
    val nextPage : Int?
)
