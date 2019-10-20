package com.rcorchero.avengers.domain.interactor

import com.rcorchero.avengers.domain.interactor.common.Interactor
import com.rcorchero.avengers.domain.model.Avengers
import com.rcorchero.avengers.domain.repository.AvengersRepository
import javax.inject.Inject

class GetAvengersInteractor
@Inject constructor(private val repository: AvengersRepository) : Interactor() {

    private var onlyOnline: Boolean = false

    override suspend fun doIt(): Avengers = repository.getAvengers(onlyOnline)

    fun setParams(onlyOnline: Boolean) {
        this.onlyOnline = onlyOnline
    }
}