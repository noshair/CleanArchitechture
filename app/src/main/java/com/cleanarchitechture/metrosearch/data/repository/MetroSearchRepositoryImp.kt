package com.cleanarchitechture.metrosearch.data.repository

import com.cleanarchitechture.di.ApiService
import com.cleanarchitechture.metrosearch.domain.repository.MetroSearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MetroSearchRepositoryImp @Inject constructor(private val apiService: ApiService) :
    MetroSearchRepository {
    override suspend fun getSearchData(categoryType: String)= flow {
        emit(apiService.getSearchData(categoryType))
    }
}