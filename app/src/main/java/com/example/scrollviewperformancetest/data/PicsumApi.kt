package com.example.scrollviewperformancetest.data

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Docs : https://picsum.photos/
 * The API will return 30 items per page by default.
 * To request another page, use the ?page parameter.
 * To change the amount of items per page, use the ?limit parameter.
 * */
interface PicsumApi {

    @GET("v2/list")
    suspend fun getPicsumImageList(
        @Query("page") page: Int
    ): List<PicsumImageInfo>
}
