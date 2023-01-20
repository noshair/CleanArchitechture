package com.cleanarchitechture.metrosearch.domain.use_case

import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearch.domain.model.SearchResponse
import com.cleanarchitechture.metrosearch.domain.repository.MetroSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllIdsUseCase @Inject
constructor(private val metroSearchRepository: MetroSearchRepository) {

    operator fun invoke(searchItem: String): Flow<Resource<SearchResponse>> = flow {
        emit(Resource.OnLoading())
        metroSearchRepository.getSearchData(searchItem).catch { error ->
            emit(Resource.OnFailure(null, error = error.message))

        }.collect {
            emit(Resource.OnSuccess(it))
        }


    }
}