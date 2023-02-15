package com.betulnecanli.sailormoonapp.data.remote.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants.SAILORMOON_CHARACTERS_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = SAILORMOON_CHARACTERS_TABLE)
data class SailorMoon(
    @SerializedName("age")
    val age: Int,
    @SerializedName("birthday")
    val birthday: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("realName")
    val realName: String,
    @SerializedName("species")
    val species: String
): Parcelable