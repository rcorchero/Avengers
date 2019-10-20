package com.rcorchero.avengers.data.source.remote

import com.rcorchero.avengers.data.entity.AvengerEntity

interface RemoteDataSource {

    @Throws(Exception::class)
    fun getAvengers(): List<AvengerEntity>
}