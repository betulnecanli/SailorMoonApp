package com.betulnecanli.sailormoonapp.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.betulnecanli.sailormoonapp.data.local.entity.SailorMoonEntity


@Dao
interface SailorMoonDAO{

    @Query("SELECT * FROM SailorMoon ORDER BY id ASC")
    fun getAllCharacters() : PagingSource<Int, SailorMoonEntity>


    @Query("SELECT * FROM SailorMoon WHERE id =:id")
    fun getSelectedCharacter(id : Int): SailorMoonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(characters : List<SailorMoonEntity>)

    @Query("DELETE FROM SailorMoon")
    suspend fun deleteAll()


}