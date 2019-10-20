package com.rcorchero.avengers.data.mapper

import com.rcorchero.avengers.data.entity.AvengerEntity
import com.rcorchero.avengers.domain.model.Avenger

fun List<AvengerEntity>.toDomain(): List<Avenger> =
    this.map { it.toDomain() }

fun AvengerEntity.toDomain(): Avenger =
    Avenger(
        abilities = abilities,
        groups = groups,
        height = height,
        name = name,
        photo = photo,
        power = power,
        realName = realName)