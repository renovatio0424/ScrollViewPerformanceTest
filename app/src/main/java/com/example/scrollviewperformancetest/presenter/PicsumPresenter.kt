package com.example.scrollviewperformancetest.presenter

import com.example.scrollviewperformancetest.data.PicsumApi
import com.example.scrollviewperformancetest.data.PicsumImageInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PicsumPresenterImpl @Inject constructor(
    private val picsumApi: PicsumApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PicsumPresenter {
    override suspend fun getPicsumImageList(page: Int): List<PicsumImageInfo> = withContext(dispatcher) {
        picsumApi.getPicsumImageList(page)
    }
}

interface PicsumPresenter {
    suspend fun getPicsumImageList(page: Int): List<PicsumImageInfo>
}
