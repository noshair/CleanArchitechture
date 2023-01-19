package com.cleanarchitechture.metrosearch.data.repository

import com.cleanarchitechture.di.ApiService
import com.cleanarchitechture.metrosearch.domain.model.SearchResponse
import com.cleanarchitechture.metrosearch.domain.repository.MetroSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MetroSearchRepositoryImp @Inject constructor(private val apiService: ApiService) :
    MetroSearchRepository {
    override suspend fun getSearchData(categoryType: String)= flow {
        emit(apiService.getSearchData(categoryType))
    }
}