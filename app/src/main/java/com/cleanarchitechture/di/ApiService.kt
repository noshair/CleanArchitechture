package com.cleanarchitechture.di

import com.appfactorycoding.service.model.search_detail.SearchDetailResponse
import com.cleanarchitechture.metrosearch.domain.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(SEARCH)
    suspend fun getSearchData(
        @Query("q") art: String,
        @Query("hasImages") hasImages: Boolean = true
    ): SearchResponse

    @GET(SEARCHITEM)
    suspend fun getGalleryItem(@Path("objectID") id: Int): SearchDetailResponse

    companion object {
        const val SEARCH = "/public/collection/v1/search?"
        const val SEARCHITEM = "/public/collection/v1/objects/{objectID}"
    }
}