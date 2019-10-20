package com.rcorchero.avengers.data

import com.rcorchero.avengers.data.entity.AvengerEntity
import com.rcorchero.avengers.data.mapper.toDomain
import com.rcorchero.avengers.data.source.local.LocalDataSource
import com.rcorchero.avengers.data.source.remote.RemoteDataSource
import com.rcorchero.avengers.domain.model.Avengers
import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.domain.repository.AvengersRepository

class AvengersRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource): AvengersRepository {

    override fun getAvengers(onlyOnline: Boolean): Avengers {
        var avengerEntityList: List<AvengerEntity>
        if (onlyOnline) {
            avengerEntityList = remoteDataSource.getAvengers()
            saveData(avengerEntityList)
        } else {
            avengerEntityList = localDataSource.getAvengers()
            if (avengerEntityList.isEmpty()) {
                avengerEntityList = remoteDataSource.getAvengers()
                saveData(avengerEntityList)
            }
        }
        return Avengers(avengers = avengerEntityList.toDomain())
    }

    private fun saveData(avengerEntityList: List<AvengerEntity>) {
        localDataSource.deleteAllAvengers()
        localDataSource.saveAvengers(avengerEntityList)
    }

    override fun getAvenger(avengerName: String): Avenger =
        localDataSource.getAvenger(avengerName)!!.toDomain()
}