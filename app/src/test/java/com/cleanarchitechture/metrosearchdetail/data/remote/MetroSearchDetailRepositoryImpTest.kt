package com.cleanarchitechture.metrosearchdetail.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.appfactorycoding.service.model.search_detail.ElementMeasurements
import com.appfactorycoding.service.model.search_detail.Measurement
import com.appfactorycoding.service.model.search_detail.SearchDetailResponse
import com.appfactorycoding.service.model.search_detail.Tag
import com.cleanarchitechture.di.ApiService
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
class MetroSearchDetailRepositoryImpTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var metroSearchDetailRepositoryImp: MetroSearchDetailRepositoryImp

    @Mock
    lateinit var apiService: ApiService
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getSelectedGalleryItem() {
        runBlocking {
            Mockito.`when`(
                apiService.getGalleryItem(38153)
            ).thenReturn(
                SearchDetailResponse(
                    "241",
                    "2015.500.4.14",
                    "2015",
                    listOf(
                        "https://images.metmuseum.org/CRDImages/as/original/DP-1062-002.jpg",
                        "https://images.metmuseum.org/CRDImages/as/original/DP-1062-003.jpg",
                        "https://images.metmuseum.org/CRDImages/as/original/DP-1062-004.jpg",
                        "https://images.metmuseum.org/CRDImages/as/original/DP-1062-005.jpg",
                        "https://images.metmuseum.org/CRDImages/as/original/DP-1062-006.jpg",
                        "https://images.metmuseum.org/CRDImages/as/original/DP244584.jpg"
                    ),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "Sculpture",
                    listOf(),
                    "",
                    "",
                    "Gift of Florence and Herbert Irving, 2015",
                    "Central India, Madhya Pradesh",
                    "Asian Art",
                    "H. 34 3/4 in. (88.3 cm); W. 20 in.(50.8 cm); D. 12 1/2 in. (31.8 cm); Wt. (with block) 170 lb (77.1 kg)",
                    "",
                    "",
                    "",
                    true,
                    true,
                    false,
                    "",
                    "",
                    "",
                    listOf(Measurement("null", ElementMeasurements(88.265175, 50.800102), "")),
                    "Sandstone",
                    "2022-08-09T15:23:22.823Z",
                    1034,
                    "mid-11th century",
                    1034,
                    38153,
                    "Figure",
                    "https://www.metmuseum.org/art/collection/search/38153",
                    "https://www.wikidata.org/wiki/Q48995767",
                    "Chandela period",
                    "",
                    "https://images.metmuseum.org/CRDImages/as/original/DP-1062-001.jpg",
                    "https://images.metmuseum.org/CRDImages/as/web-large/DP-1062-001.jpg",
                    "",
                    "",
                    "Metropolitan Museum of Art, New York, NY",
                    "",
                    "",
                    "",
                    "",
                    listOf(
                        Tag(
                            "Dancers",
                            "http://vocab.getty.edu/page/aat/300025653",
                            "https://www.wikidata.org/wiki/Q5716684"
                        )
                    ),
                    "Celestial dancer (Devata)"
                )
            )


            val sut = MetroSearchDetailRepositoryImp(
                apiService
            )

            val result = sut.getSelectedGalleryItem(
                38153
            )

            result.test {
                val output = awaitItem()
                assertEquals("241", output.GalleryNumber)
                awaitComplete()
            }
        }
    }
}

