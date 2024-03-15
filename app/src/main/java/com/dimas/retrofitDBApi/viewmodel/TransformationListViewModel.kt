package com.dimas.retrofitDBApi.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dimas.retrofitDBApi.di.DaggerApiComponent

import com.dimas.retrofitDBApi.model.Transformations
import com.dimas.retrofitDBApi.model.TransformationsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransformationListViewModel: ViewModel() {

    @Inject
    lateinit var transformationsService: TransformationsService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val transformations = MutableLiveData<List<Transformations>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchTransformations()
    }

    private fun fetchTransformations() {
        loading.value = true
        disposable.add(
            transformationsService.getTransformations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Transformations>>() {
                    override fun onSuccess(value: List<Transformations>?) {
                        transformations.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}