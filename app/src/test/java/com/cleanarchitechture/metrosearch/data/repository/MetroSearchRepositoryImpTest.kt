package com.cleanarchitechture.metrosearch.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.cleanarchitechture.di.ApiService
import com.cleanarchitechture.metrosearch.domain.model.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MetroSearchRepositoryImpTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var metroSearchRepositoryImp: MetroSearchRepositoryImp

    @Mock
    lateinit var apiService: ApiService
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getSearchData() {
        val testOutSide = SearchResponse(listOf(4, 5), 2)
        runBlocking {
            Mockito.`when`(
                apiService.getSearchData("art")
            ).thenReturn(
                testOutSide
            )

            val sut = MetroSearchRepositoryImp(
                apiService
            )

            val result = sut.getSearchData(
                "art"
            )

            result.test {
                val output = awaitItem()
                assertEquals(2, output.total)
                awaitComplete()
            }
        }

    }

}