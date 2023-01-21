package com.cleanarchitechture.di

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    lateinit var mockWebService: MockWebServer
    lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebService = MockWebServer()
        apiService = Retrofit.Builder().baseUrl(mockWebService.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Test
    fun itemDetailCheck() {
        runBlocking {
            val mockResponse = MockResponse()
            mockResponse.setBody(
                "{\n" +
                        "    \"objectID\": 38153,\n" +
                        "    \"isHighlight\": true,\n" +
                        "    \"accessionNumber\": \"2015.500.4.14\",\n" +
                        "    \"accessionYear\": \"2015\",\n" +
                        "    \"isPublicDomain\": true,\n" +
                        "    \"primaryImage\": \"https://images.metmuseum.org/CRDImages/as/original/DP-1062-001.jpg\",\n" +
                        "    \"primaryImageSmall\": \"https://images.metmuseum.org/CRDImages/as/web-large/DP-1062-001.jpg\",\n" +
                        "    \"additionalImages\": [\n" +
                        "        \"https://images.metmuseum.org/CRDImages/as/original/DP-1062-002.jpg\",\n" +
                        "        \"https://images.metmuseum.org/CRDImages/as/original/DP-1062-003.jpg\",\n" +
                        "        \"https://images.metmuseum.org/CRDImages/as/original/DP-1062-004.jpg\",\n" +
                        "        \"https://images.metmuseum.org/CRDImages/as/original/DP-1062-005.jpg\",\n" +
                        "        \"https://images.metmuseum.org/CRDImages/as/original/DP-1062-006.jpg\",\n" +
                        "        \"https://images.metmuseum.org/CRDImages/as/original/DP244584.jpg\"\n" +
                        "    ],\n" +
                        "    \"constituents\": null,\n" +
                        "    \"department\": \"Asian Art\",\n" +
                        "    \"objectName\": \"Figure\",\n" +
                        "    \"title\": \"Celestial dancer (Devata)\",\n" +
                        "    \"culture\": \"Central India, Madhya Pradesh\",\n" +
                        "    \"period\": \"Chandela period\",\n" +
                        "    \"dynasty\": \"\",\n" +
                        "    \"reign\": \"\",\n" +
                        "    \"portfolio\": \"\",\n" +
                        "    \"artistRole\": \"\",\n" +
                        "    \"artistPrefix\": \"\",\n" +
                        "    \"artistDisplayName\": \"\",\n" +
                        "    \"artistDisplayBio\": \"\",\n" +
                        "    \"artistSuffix\": \"\",\n" +
                        "    \"artistAlphaSort\": \"\",\n" +
                        "    \"artistNationality\": \"\",\n" +
                        "    \"artistBeginDate\": \"\",\n" +
                        "    \"artistEndDate\": \"\",\n" +
                        "    \"artistGender\": \"\",\n" +
                        "    \"artistWikidata_URL\": \"\",\n" +
                        "    \"artistULAN_URL\": \"\",\n" +
                        "    \"objectDate\": \"mid-11th century\",\n" +
                        "    \"objectBeginDate\": 1034,\n" +
                        "    \"objectEndDate\": 1066,\n" +
                        "    \"medium\": \"Sandstone\",\n" +
                        "    \"dimensions\": \"H. 34 3/4 in. (88.3 cm); W. 20 in.(50.8 cm); D. 12 1/2 in. (31.8 cm); Wt. (with block) 170 lb (77.1 kg)\",\n" +
                        "    \"measurements\": [\n" +
                        "        {\n" +
                        "            \"elementName\": \"Overall\",\n" +
                        "            \"elementDescription\": \"with block\",\n" +
                        "            \"elementMeasurements\": {\n" +
                        "                \"Weight\": 77.111496\n" +
                        "            }\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"elementName\": \"Overall\",\n" +
                        "            \"elementDescription\": null,\n" +
                        "            \"elementMeasurements\": {\n" +
                        "                \"Depth\": 31.750063,\n" +
                        "                \"Height\": 88.265175,\n" +
                        "                \"Width\": 50.800102\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"creditLine\": \"Gift of Florence and Herbert Irving, 2015\",\n" +
                        "    \"geographyType\": \"\",\n" +
                        "    \"city\": \"\",\n" +
                        "    \"state\": \"\",\n" +
                        "    \"county\": \"\",\n" +
                        "    \"country\": \"\",\n" +
                        "    \"region\": \"\",\n" +
                        "    \"subregion\": \"\",\n" +
                        "    \"locale\": \"\",\n" +
                        "    \"locus\": \"\",\n" +
                        "    \"excavation\": \"\",\n" +
                        "    \"river\": \"\",\n" +
                        "    \"classification\": \"Sculpture\",\n" +
                        "    \"rightsAndReproduction\": \"\",\n" +
                        "    \"linkResource\": \"\",\n" +
                        "    \"metadataDate\": \"2022-08-09T15:23:22.823Z\",\n" +
                        "    \"repository\": \"Metropolitan Museum of Art, New York, NY\",\n" +
                        "    \"objectURL\": \"https://www.metmuseum.org/art/collection/search/38153\",\n" +
                        "    \"tags\": [\n" +
                        "        {\n" +
                        "            \"term\": \"Dancers\",\n" +
                        "            \"AAT_URL\": \"http://vocab.getty.edu/page/aat/300025653\",\n" +
                        "            \"Wikidata_URL\": \"https://www.wikidata.org/wiki/Q5716684\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"objectWikidata_URL\": \"https://www.wikidata.org/wiki/Q48995767\",\n" +
                        "    \"isTimelineWork\": false,\n" +
                        "    \"GalleryNumber\": \"241\"\n" +
                        "}"
            )
            mockWebService.enqueue(mockResponse)


            val result = apiService.getGalleryItem(38204)
            mockWebService.takeRequest()
            Assert.assertEquals("241", result.GalleryNumber)
        }
    }

    @Test
    fun getSearch() {
        runBlocking {
            val mockResponse = MockResponse()
            mockResponse.setBody("{}")
            mockWebService.enqueue(mockResponse)


            val result = apiService.getSearchData("art")
            mockWebService.takeRequest()
            Assert.assertEquals(null, result.objectIDs)
        }
    }

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

}