package com.rcorchero.avengers.data.source.remote

import com.rcorchero.avengers.data.entity.AvengersEntity
import com.rcorchero.avengers.data.entity.AvengerEntity

class RemoteDataSourceImpl(apiService: APIService) : Repository(apiService), RemoteDataSource {

    @Throws(Exception::class)
    override fun getAvengers(): List<AvengerEntity> {
        val executeCall = executeCall(apiService.getAvengersList())
        val response = executeCall.body() as AvengersEntity
        return response.avengers
    }
}