package com.rcorchero.avengers.data.source.local

import com.rcorchero.avengers.data.entity.AvengerEntity

interface LocalDataSource {

    fun getAvengers(): List<AvengerEntity>

    fun getAvenger(avengerName: String): AvengerEntity?

    fun saveAvengers(avengerEntityList: List<AvengerEntity>)

    fun deleteAllAvengers()
}