package com.cleanarchitechture.metrosearchdetail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearchdetail.domain.model.OwnSearchDetail
import com.cleanarchitechture.metrosearchdetail.domain.use_case.GetSelectedIItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MetroSearchDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MetroSearchDetailViewModel

    @Mock
    lateinit var getSelectedIItemUseCase: GetSelectedIItemUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MetroSearchDetailViewModel(getSelectedIItemUseCase)
    }

    @Test
    fun `code for success`() {
        runTest {
            Mockito.`when`(
                getSelectedIItemUseCase.invoke(38153)
            ).thenReturn(flow {
                emit(
                    Resource.OnSuccess(
                        OwnSearchDetail(
                            "241",
                            "2015.500.4.14",
                            "2015",
                            "Figure", listOf(
                                "https://images.metmuseum.org/CRDImages/as/original/DP-1062-002.jpg",
                                "https://images.metmuseum.org/CRDImages/as/original/DP-1062-003.jpg",
                                "https://images.metmuseum.org/CRDImages/as/original/DP-1062-004.jpg",
                            )
                        )
                    )
                )
            }
            )
            viewModel.getSelectedItem(38153)
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.searchDetailItem.test {
                val items = awaitItem()
                assertEquals("Figure", items.ItemList?.objectName ?: "")

            }
        }
    }

    @Test
    fun `code for failure`() {
        runTest {
            Mockito.`when`(
                getSelectedIItemUseCase.invoke(38153)
            ).thenReturn(flow {
                emit(
                    Resource.OnFailure(
                        null, "401 Error"
                    )
                )
            }
            )
            viewModel.getSelectedItem(38153)
            testDispatcher.scheduler.advanceUntilIdle()

            viewModel.searchDetailItem
            viewModel.searchDetailItem.test {
                val items = awaitItem()
                assertEquals("401 Error", items.error)
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}