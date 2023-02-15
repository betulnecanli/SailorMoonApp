package com.betulnecanli.sailormoonapp.repository

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class Repository @Inject constructor(
        private val remoteDataSource: RemoteDataSource
) {

    fun getAllCharacters(): Flow<PagingData<SailorMoon>> {
        return remoteDataSource.getAllCharacters()
    }
}