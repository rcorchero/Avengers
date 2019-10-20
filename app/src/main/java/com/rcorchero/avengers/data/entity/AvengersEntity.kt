package com.rcorchero.avengers.data.entity

import com.google.gson.annotations.SerializedName

data class AvengersEntity(
    @SerializedName("superheroes") val avengers: List<AvengerEntity>
)