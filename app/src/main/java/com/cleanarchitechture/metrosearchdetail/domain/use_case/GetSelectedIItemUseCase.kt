package com.cleanarchitechture.metrosearchdetail.domain.use_case

import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearchdetail.domain.model.OwnSearchDetail
import com.cleanarchitechture.metrosearchdetail.domain.repository.MetroSearchDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedIItemUseCase @Inject constructor(private val metroSearchDetailRepository: MetroSearchDetailRepository) {

    operator fun invoke(searchItem: Int): Flow<Resource<OwnSearchDetail>> = flow {

        emit(Resource.OnLoading())
        metroSearchDetailRepository.getSelectedGalleryItem(searchItem)
            .map {
                OwnSearchDetail(
                    it.title,
                    it.department,
                    it.primaryImage,
                    it.objectName,
                    it.additionalImages
                )
            }.catch { error ->
                emit(Resource.OnFailure(null, error = error.message))
            }.collect {
                emit(Resource.OnSuccess(it))
            }
    }
}