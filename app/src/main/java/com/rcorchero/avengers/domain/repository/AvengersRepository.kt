package com.rcorchero.avengers.domain.repository

import com.rcorchero.avengers.domain.model.Avengers
import com.rcorchero.avengers.domain.model.Avenger

interface AvengersRepository {

    @Throws(Exception::class)
    fun getAvengers(onlyOnline: Boolean): Avengers

    fun getAvenger(avengerName: String): Avenger
}