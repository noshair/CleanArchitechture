package com.cleanarchitechture.metrosearchdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearchdetail.domain.model.DetailUiState
import com.cleanarchitechture.metrosearchdetail.domain.use_case.GetSelectedIItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
    private var fetchJob: Job? = null

    //work  on
    fun getSelectedItem(id: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            getSelectedIItemUseCase(id).collect { item ->
                when (item) {
                    is Resource.OnLoading -> {
                        searchDetailMutable.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.OnSuccess -> {
                        searchDetailMutable.update {
                            it.copy(isLoading = false)
                        }
                        searchDetailMutable.update {
                            DetailUiState(ItemList = item.data)
                        }
                    }
                    is Resource.OnFailure -> {
                        searchDetailMutable.update {
                            it.copy(isLoading = false)
                        }
                        searchDetailMutable.update {
                            it.copy(
                                error = item.error ?: "unexpected error occurred",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}