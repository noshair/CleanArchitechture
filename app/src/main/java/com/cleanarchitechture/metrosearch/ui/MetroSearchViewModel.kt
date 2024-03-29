package com.cleanarchitechture.metrosearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearch.domain.model.SearchUi
import com.cleanarchitechture.metrosearch.domain.use_case.GetAllIdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetroSearchViewModel @Inject constructor(private val getAllidsUseCase: GetAllIdsUseCase) :
    ViewModel() {
    private var searchMutableList = MutableStateFlow(SearchUi())
    val searchList = searchMutableList.asStateFlow()

    // private var fetchJob: Job? = null
    fun getGalleryIds(category: String) {
        //fetchJob?.cancel()
        viewModelScope.launch {
            getAllidsUseCase(category).collect { item ->
                when (item) {
                    is Resource.OnLoading -> {
                        searchMutableList.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.OnSuccess -> {
                        searchMutableList.update { it.copy(isLoading = false) }
                        searchMutableList.update {
                            it.copy(
                                coinList = item.data?.objectIDs ?: emptyList()
                            )
                        }
                    }

                    is Resource.OnFailure -> {
                        searchMutableList.update { it.copy(isLoading = false) }
                        searchMutableList.update {
                            it.copy(
                                error = item.error ?: "unexpected error occurred"
                            )
                        }
                    }
                }
            }
        }
    }
}