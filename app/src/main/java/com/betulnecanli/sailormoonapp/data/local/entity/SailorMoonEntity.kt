package com.betulnecanli.sailormoonapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SailorMoon")
data class SailorMoonEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val name :String,
    val image : String,
    val realName: String,
    val age : Int,
    val birth : String,
    val species : String
)
