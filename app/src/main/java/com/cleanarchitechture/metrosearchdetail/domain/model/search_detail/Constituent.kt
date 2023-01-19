package com.appfactorycoding.service.model.search_detail

data class Constituent(
    val constituentID: Int,
    val constituentULAN_URL: String,
    val constituentWikidata_URL: String,
    val gender: String,
    val name: String,
    val role: String
)