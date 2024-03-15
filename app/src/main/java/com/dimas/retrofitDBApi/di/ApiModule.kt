package com.dimas.retrofitDBApi.di


import com.dimas.retrofitDBApi.model.TransformationsApi
import com.dimas.retrofitDBApi.model.TransformationsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_DB_API_URL = "https://dragonball-api.com"

    @Provides
    fun provideTrasnformationsApi(): TransformationsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_DB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TransformationsApi::class.java)
    }

    @Provides
    fun provideTransformationsService(): TransformationsService {
        return TransformationsService()
    }
}