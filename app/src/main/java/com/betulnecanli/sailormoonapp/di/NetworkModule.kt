package com.betulnecanli.sailormoonapp.di

import androidx.paging.ExperimentalPagingApi
import com.betulnecanli.sailormoonapp.data.local.SailorMoonDB
import com.betulnecanli.sailormoonapp.data.repository.RemoteDataSourceImpl
import com.betulnecanli.sailormoonapp.network.RemoteDataSource
import com.betulnecanli.sailormoonapp.network.SailorMoonAPI
import com.betulnecanli.sailormoonapp.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun getInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit{
       val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSailorMoonApi(retrofit: Retrofit) : SailorMoonAPI{
        return retrofit.create(SailorMoonAPI::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Singleton
    @Provides
    fun provideRemoteDataSource(
        api :SailorMoonAPI,
        db : SailorMoonDB
    ): RemoteDataSource{
        return RemoteDataSourceImpl(api, db)
    }


}