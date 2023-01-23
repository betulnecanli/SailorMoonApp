package com.betulnecanli.sailormoonapp.utils

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertList2String(list : List<String>): String{
        val stringBuilder = StringBuilder()
        for(item in list){
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertString2List(string : String) : List<String>{
        return string.split(separator)
    }
}