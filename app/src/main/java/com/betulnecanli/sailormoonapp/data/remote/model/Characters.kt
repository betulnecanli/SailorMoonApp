package com.betulnecanli.sailormoonapp.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.SAILORMOON_CHARACTERS_TABLE)
data class Characters(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val name :String,
    val image : String,
    val realName: String,
    val age : Int,
    val birth : String,
    val species : String
)
