package com.cleanarchitechture.metrosearchdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearchdetail.domain.model.DetailUiState
import com.cleanarchitechture.metrosearchdetail.domain.use_case.GetSelectedIItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetroSearchDetailViewModel
@Inject constructor
    (private val getSelectedIItemUseCase: GetSelectedIItemUseCase) : ViewModel() {
    private val searchDetailMutable = MutableStateFlow(DetailUiState())

    val searchDetailItem = searchDetailMutable.asStateFlow()

    //work  on
    fun getSelectedItem(id: Int) {
        viewModelScope.launch {
            getSelectedIItemUseCase(id).collect { item ->
                when (item) {
                    is Resource.OnLoading -> {
                        searchDetailMutable.update {
                            DetailUiState(isLoading = true, null)
                        }
                    }
                    is Resource.OnSuccess -> {
                        searchDetailMutable.update { DetailUiState(isLoading = false) }
                        searchDetailMutable.update {
                            DetailUiState(ItemList = item.data)
                        }
                    }
                    is Resource.OnFailure -> {
                        searchDetailMutable.update { DetailUiState(isLoading = false) }
                        searchDetailMutable.update {
                            DetailUiState(
                                error = item.error ?: "unexpected error occurred",
                                isLoading = false
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}