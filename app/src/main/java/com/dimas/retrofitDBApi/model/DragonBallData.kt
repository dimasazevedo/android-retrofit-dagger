package com.dimas.retrofitDBApi.model


import com.google.gson.annotations.SerializedName

data class Transformations(
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("ki")
    val ki: String?
)

