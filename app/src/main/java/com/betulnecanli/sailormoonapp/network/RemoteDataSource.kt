package com.betulnecanli.sailormoonapp.network

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllCharacters() : Flow<PagingData<SailorMoon>>
    fun searchCharacters() : Flow<PagingData<SailorMoon>>
}