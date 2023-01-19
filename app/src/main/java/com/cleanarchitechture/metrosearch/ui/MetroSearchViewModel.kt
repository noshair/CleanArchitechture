package com.cleanarchitechture.metrosearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearch.domain.model.SearchUi
import com.cleanarchitechture.metrosearch.domain.use_case.GetAllIdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MetroSearchViewModel @Inject
constructor(private val getAllidsUseCase: GetAllIdsUseCase) : ViewModel() {
    private var searchMutableList = MutableStateFlow(SearchUi())

    val searchList = searchMutableList.asStateFlow()
    init {
        getGalleryIds()
    }
    private fun getGalleryIds(){
        getAllidsUseCase("art").onEach {item->
            when (item) {
                is Resource.OnLoading -> {
                    searchMutableList.update {
                        SearchUi(true)
                    }
                }
                is Resource.OnSuccess -> {
                    searchMutableList.update { SearchUi(false) }
                    searchMutableList.update {
                        SearchUi(coinList = item.data?.objectIDs ?: emptyList())
                    }
                }
                is Resource.OnFailure -> {
                    searchMutableList.update { SearchUi(false) }
                    searchMutableList.update {
                        SearchUi(error = item.error?: "unexpected error accord")
                    }
                }
                else ->{}
            }
        }.launchIn(viewModelScope)
    }
}