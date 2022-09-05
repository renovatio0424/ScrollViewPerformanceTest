package com.example.scrollviewperformancetest.data

import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumApi {

    @GET("v2/list")
    suspend fun getPicsumImageList(
        @Query("page") page: Int
    ): List<PicsumImageInfo>
}
