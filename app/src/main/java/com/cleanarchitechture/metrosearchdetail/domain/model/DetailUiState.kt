package com.cleanarchitechture.metrosearchdetail.domain.model

data class DetailUiState(
    val isLoading: Boolean = false,
    val ItemList: OwnSearchDetail? = null,
    val error: String = ""
)