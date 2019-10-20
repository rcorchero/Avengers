package com.rcorchero.avengers.domain.model

data class Avenger(
    val abilities: String,
    val groups: String,
    val height: String,
    val name: String,
    val photo: String,
    val power: String,
    val realName: String
) : DomainModel()