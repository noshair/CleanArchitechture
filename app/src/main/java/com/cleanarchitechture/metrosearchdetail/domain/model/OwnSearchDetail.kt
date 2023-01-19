package com.cleanarchitechture.metrosearchdetail.domain.model

data class OwnSearchDetail(
    val title: String,
    val department: String,
    val primaryImage: String,
    val objectName: String,
    val additionalImages: List<String>
)
