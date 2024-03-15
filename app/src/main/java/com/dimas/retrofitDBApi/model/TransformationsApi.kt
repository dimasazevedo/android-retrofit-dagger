package com.dimas.retrofitDBApi.model


import io.reactivex.Single
import retrofit2.http.GET

interface TransformationsApi {
    @GET("api/transformations")
    fun getTransformations(): Single<List<Transformations>>
}

