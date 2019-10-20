package com.rcorchero.avengers.presentation


interface DetailAvengerView : BaseView {

    fun getAvengerName(): String

    fun displayImage(url: String)
    fun displayName(name: String)

    fun displayRealName(realName: String)
    fun displayHeight(height: String)
    fun displayAbilities(abilities: String)
    fun displayPowers(powers: String)
    fun displayGroups(groups: String)

    fun goBack()
}