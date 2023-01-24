package com.betulnecanli.sailormoonapp.data.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.betulnecanli.sailormoonapp.data.local.SailorMoonDB
import com.betulnecanli.sailormoonapp.data.remote.model.Characters
import com.betulnecanli.sailormoonapp.network.SailorMoonAPI
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SailorMoonRemoteMediator @Inject constructor(
    private val sailorMoonAPI: SailorMoonAPI,
    private val sailorMoonDB: SailorMoonDB
): RemoteMediator<Int, Characters>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Characters>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}