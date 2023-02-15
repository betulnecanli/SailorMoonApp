package com.betulnecanli.sailormoonapp.data.pagingsource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.betulnecanli.sailormoonapp.data.local.SailorMoonDB
import com.betulnecanli.sailormoonapp.data.local.entities.RemoteKeysEntity
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.network.SailorMoonAPI
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SailorMoonRemoteMediator @Inject constructor(
    private val sailorMoonAPI: SailorMoonAPI,
    private val sailorMoonDB: SailorMoonDB
): RemoteMediator<Int, SailorMoon>() {

    private val sailorMoonDAO = sailorMoonDB.sailorMoonDao()
    private val remoteKeysDAO = sailorMoonDB.remoteKeysDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SailorMoon>
    ): MediatorResult {
        return try{
            val page = when(loadType){
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
            }

            val response = sailorMoonAPI.getCharacters(page = page)
            if (response.sailorMoon.isNotEmpty()) {
                Log.d("asdfgh0", response.sailorMoon.get(0).name)
                sailorMoonDB.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        sailorMoonDAO.deleteAll()
                        remoteKeysDAO.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPAge = response.nextPage
                    val keys = response.sailorMoon.map { characters ->
                        RemoteKeysEntity(
                            id = characters.id,
                            prevPage = prevPage,
                            nextPage = nextPAge
                        )
                    }

                    remoteKeysDAO.addAllRemoteKeys(keys)
                    sailorMoonDAO.addCharacter(response.sailorMoon)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch(e : Exception) {
            Log.d("asdfgh02", e.toString())

            return MediatorResult.Error(e)
        }
    }



    private suspend fun getRemoteKeyFirstItem(state: PagingState<Int, SailorMoon>): RemoteKeysEntity? {
            return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()
        ?.let {
            remoteKeysDAO.getRemoteKey(it.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, SailorMoon>): RemoteKeysEntity? {
            return state.pages.lastOrNull(){it.data.isNotEmpty()}?.data?.lastOrNull()
                ?.let {
                    remoteKeysDAO.getRemoteKey(it.id)
                }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, SailorMoon>): RemoteKeysEntity? {
    return state.anchorPosition?.let {position ->
        state.closestItemToPosition(position)?.id?.let {id->
            remoteKeysDAO.getRemoteKey(id=id )

        }
    }
    }
}