package com.rcorchero.avengers.data.source.remote

import com.rcorchero.avengers.data.entity.AvengersEntity
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("/bins/bvyob")
    fun getAvengersList(): Call<AvengersEntity>
}