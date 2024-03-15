package com.dimas.retrofitDBApi.di



import com.dimas.retrofitDBApi.model.TransformationsService
import com.dimas.retrofitDBApi.viewmodel.TransformationListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: TransformationsService)
    fun inject(viewModel: TransformationListViewModel)

}

