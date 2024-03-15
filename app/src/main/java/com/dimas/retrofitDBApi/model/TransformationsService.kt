package com.dimas.retrofitDBApi.model


import com.dimas.retrofitDBApi.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class TransformationsService {

    @Inject
    lateinit var api: TransformationsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getTransformations(): Single<List<Transformations>> {
        return api.getTransformations()
    }
}