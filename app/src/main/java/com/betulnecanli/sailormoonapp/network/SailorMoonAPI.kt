package com.betulnecanli.sailormoonapp.network

import com.betulnecanli.sailormoonapp.data.remote.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SailorMoonAPI {

    @GET("/sailormoon/characters")
    suspend fun getCharacters(
        @Query("page") page : Int = 1
    ) : ApiResponse














}