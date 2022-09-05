package com.example.scrollviewperformancetest.di

import com.example.scrollviewperformancetest.BASE_URL_PICSUM_API
import com.example.scrollviewperformancetest.data.PicsumApi
import com.example.scrollviewperformancetest.presenter.PicsumPresenter
import com.example.scrollviewperformancetest.presenter.PicsumPresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object PicsumModule {

    @Provides
    fun providePicsumService(): PicsumApi = Retrofit.Builder()
        .baseUrl(BASE_URL_PICSUM_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PicsumApi::class.java)

    @Provides
    fun providePicsumPresenter(picsumApi: PicsumApi): PicsumPresenter = PicsumPresenterImpl(picsumApi)
}
