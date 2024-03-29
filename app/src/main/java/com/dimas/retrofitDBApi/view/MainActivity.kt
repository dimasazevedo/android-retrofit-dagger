package com.dimas.retrofitDBApi.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.dimas.retrofitDBApi.R
import com.dimas.retrofitDBApi.viewmodel.TransformationListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TransformationListViewModel
    private val transformationsAdapter = TransformationsListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(TransformationListViewModel::class.java)
        viewModel.refresh()

        transformationsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transformationsAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }



    private fun observeViewModel() {
        viewModel.transformations.observe(this, Observer {transformations ->
            transformations?.let {
                transformationsList.visibility = View.VISIBLE
                transformationsAdapter.updateTransformations(it) }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    transformationsList.visibility = View.GONE
                }
            }
        })
    }
}

