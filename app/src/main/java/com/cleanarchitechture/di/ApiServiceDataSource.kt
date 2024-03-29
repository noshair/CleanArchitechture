package com.cleanarchitechture.di

import com.cleanarchitechture.BuildConfig.API_BASE_URL
import com.cleanarchitechture.metrosearch.data.repository.MetroSearchRepositoryImp
import com.cleanarchitechture.metrosearch.domain.repository.MetroSearchRepository
import com.cleanarchitechture.metrosearchdetail.data.remote.MetroSearchDetailRepositoryImp
import com.cleanarchitechture.metrosearchdetail.domain.repository.MetroSearchDetailRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceDataSource {

    private val client = OkHttpClient().newBuilder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun retrofitObject(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun apiServiceProvider(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideSearchRepository(apiService: ApiService): MetroSearchRepository {
        return MetroSearchRepositoryImp(apiService)
    }

    @Singleton
    @Provides
    fun provideSearchDetailRepository(apiService: ApiService): MetroSearchDetailRepository {
        return MetroSearchDetailRepositoryImp(apiService)
    }
}