package com.cleanarchitechture.metrosearch.domain.model

data class SearchResponse(
    val objectIDs: List<Int>,
    val total: Int
)