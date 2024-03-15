package com.dimas.retrofitDBApi.view


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimas.retrofitDBApi.R

import com.dimas.retrofitDBApi.model.Transformations
import com.dimas.retrofitDBApi.util.getProgressDrawable
import com.dimas.retrofitDBApi.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class TransformationsListAdapter(var transformations: ArrayList<Transformations>):RecyclerView.Adapter<TransformationsListAdapter.TransformationsViewHolder>() {

    fun updateTransformations(newTransformations: List<Transformations>) {
        transformations.clear()
        transformations.addAll(newTransformations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = TransformationsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: TransformationsViewHolder, position: Int) {
        holder.bind(transformations[position])
    }

    override fun getItemCount() = transformations.size


    class TransformationsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val tranformationName = view.name
        private val transformationKi = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(transformations: Transformations) {
            tranformationName.text = transformations.name
            transformationKi.text = transformations.ki
            imageView.loadImage(transformations.image, progressDrawable)

        }
    }




}