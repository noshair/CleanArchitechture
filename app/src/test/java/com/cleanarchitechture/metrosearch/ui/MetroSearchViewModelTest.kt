package com.cleanarchitechture.metrosearch.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearch.domain.model.SearchResponse
import com.cleanarchitechture.metrosearch.domain.use_case.GetAllIdsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MetroSearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MetroSearchViewModel

    @Mock
    lateinit var getAllIdsUseCase: GetAllIdsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MetroSearchViewModel(getAllIdsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `code for success`() {
        runTest {
            val testOutSide = SearchResponse(listOf(4, 5), 2)
            Mockito.`when`(
                getAllIdsUseCase.invoke("art")
            ).thenReturn(flow {
                emit(
                    Resource.OnSuccess(
                        testOutSide
                    )
                )
            }
            )
            viewModel.getGalleryIds("art")
            testDispatcher.scheduler.advanceUntilIdle()

            viewModel.searchList.test {
                val items = awaitItem()
                Assert.assertEquals(2, items.coinList.size)
            }
        }
    }

    @Test
    fun `code for failure`() {
        runTest {
            Mockito.`when`(
                getAllIdsUseCase.invoke("art")
            ).thenReturn(flow {
                emit(
                    Resource.OnFailure(
                        null, "401 Error"
                    )
                )
            }
            )
            viewModel.getGalleryIds("art")
            testDispatcher.scheduler.advanceUntilIdle()

            viewModel.searchList.test {
                val items = awaitItem()
                Assert.assertEquals("401 Error", items.error)
            }
        }
    }
}