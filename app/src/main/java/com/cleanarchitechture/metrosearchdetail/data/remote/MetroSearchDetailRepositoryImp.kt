package com.cleanarchitechture.metrosearchdetail.data.remote

import com.cleanarchitechture.di.ApiService
import com.cleanarchitechture.metrosearchdetail.domain.repository.MetroSearchDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MetroSearchDetailRepositoryImp(private val apiService: ApiService) :
    MetroSearchDetailRepository {
    override suspend fun getSelectedGalleryItem(objectID: Int) = flow {
        emit(apiService.getGalleryItem(objectID))
    }.flowOn(Dispatchers.IO)
}