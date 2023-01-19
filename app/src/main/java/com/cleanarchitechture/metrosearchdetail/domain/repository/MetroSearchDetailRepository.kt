package com.cleanarchitechture.metrosearchdetail.domain.repository

import com.appfactorycoding.service.model.search_detail.SearchDetailResponse
import kotlinx.coroutines.flow.Flow

interface MetroSearchDetailRepository {
    suspend fun getSelectedGalleryItem(objectID: Int): Flow<SearchDetailResponse>
}