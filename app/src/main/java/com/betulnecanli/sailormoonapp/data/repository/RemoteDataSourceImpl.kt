package com.betulnecanli.sailormoonapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.local.SailorMoonDB
import com.betulnecanli.sailormoonapp.data.pagingsource.SailorMoonRemoteMediator
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.network.RemoteDataSource
import com.betulnecanli.sailormoonapp.network.SailorMoonAPI
import kotlinx.coroutines.flow.Flow


@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val api : SailorMoonAPI,
    private val db : SailorMoonDB
): RemoteDataSource {

    private val SailorDao = db.sailorMoonDao()
    private val RemoteKeysDao = db.remoteKeysDao()

    override fun getAllCharacters(): Flow<PagingData<SailorMoon>> {

        val pagingSourceFactory = {SailorDao.getAllCharacters()}
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = SailorMoonRemoteMediator(
                api, db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

    override fun searchCharacters(): Flow<PagingData<SailorMoon>> {
        TODO("Not yet implemented")
    }
}