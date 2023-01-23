package com.betulnecanli.sailormoonapp.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = Constants.REMOTEKEY_TABLE)
data class RemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val prevPage : Int?,
    val nextPage : Int?
)
