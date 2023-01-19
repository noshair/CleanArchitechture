package com.cleanarchitechture.metrosearch.domain.repository

import com.cleanarchitechture.metrosearch.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow

interface MetroSearchRepository {
   suspend fun getSearchData(categoryType:String): Flow<SearchResponse>
}