package com.rcorchero.avengers.domain.interactor

import com.rcorchero.avengers.domain.interactor.common.Interactor
import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.domain.repository.AvengersRepository
import javax.inject.Inject

class GetAvengerInteractor
@Inject constructor(private val repository: AvengersRepository) : Interactor() {

    private var avengerName: String = ""

    override suspend fun doIt(): Avenger = repository.getAvenger(avengerName)

    fun setParams(avengerName: String) {
        this.avengerName = avengerName
    }
}