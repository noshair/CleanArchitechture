package com.cleanarchitechture.metrosearchdetail.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cleanarchitechture.metrosearchdetail.domain.repository.MetroSearchDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class GetSelectedIItemUseCaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var metroSearchDetailRepository: MetroSearchDetailRepository

    @Mock
    lateinit var getSelectedIItemUseCase: GetSelectedIItemUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getSelectedItemUseCase() {
        getSelectedIItemUseCase(12328)
    }
}