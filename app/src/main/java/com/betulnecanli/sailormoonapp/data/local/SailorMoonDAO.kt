package com.betulnecanli.sailormoonapp.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon


@Dao
interface SailorMoonDAO{

    @Query("SELECT * FROM SailorMoon ORDER BY id ASC")
    fun getAllCharacters() : PagingSource<Int, SailorMoon>


    @Query("SELECT * FROM SailorMoon WHERE id =:id")
    fun getSelectedCharacter(id : Int): SailorMoon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(characters : List<SailorMoon>)

    @Query("DELETE FROM SailorMoon")
    suspend fun deleteAll()


}