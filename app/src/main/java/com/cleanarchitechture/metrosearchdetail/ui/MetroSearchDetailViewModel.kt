package com.cleanarchitechture.metrosearchdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appfactorycoding.service.model.search_detail.SearchDetailResponse
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearchdetail.domain.repository.MetroSearchDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetroSearchDetailViewModel
@Inject constructor
    (private val metroSearchDetailRepository: MetroSearchDetailRepository) : ViewModel() {
    private val searchDetailMutable =
        MutableStateFlow<Resource<SearchDetailResponse>>(Resource.OnNothing())

    val searchDetailItem: StateFlow<Resource<SearchDetailResponse>>
        get() = searchDetailMutable

    fun getSelectedItem(id: Int) {
        viewModelScope.launch {
            searchDetailMutable.value = Resource.OnLoading()
            metroSearchDetailRepository.getSelectedGalleryItem(id)
                .catch { e ->
                    searchDetailMutable.value = Resource.OnFailure(null, e.message)
                }.collect {
                    searchDetailMutable.value = Resource.OnSuccess(it)
                }
        }
    }
}