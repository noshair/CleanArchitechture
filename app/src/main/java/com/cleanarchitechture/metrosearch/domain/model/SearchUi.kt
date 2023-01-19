package com.cleanarchitechture.metrosearch.domain.model

data class SearchUi (
    val isLoading: Boolean = false,
    val coinList: List<Int> = emptyList(),
    val error:String=""
)
