package com.rcorchero.avengers.presentation.presenters

import com.rcorchero.avengers.domain.model.DomainModel
import com.rcorchero.avengers.domain.interactor.GetAvengerInteractor
import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.presentation.DetailAvengerView
import javax.inject.Inject

class DetailAvengerPresenter
@Inject constructor(private val getAvengerInteractor: GetAvengerInteractor): BasePresenter<DetailAvengerView>() {

    override fun onViewAttached() {
        view.showLoading()

        getAvengerInteractor.setParams(view.getAvengerName())
        execute(getAvengerInteractor, this::onSuccess)
    }

    private fun onSuccess(data: DomainModel) {
        view.hideLoading()

        val avenger = data as Avenger

        view.let {
            it.displayImage(avenger.photo)
            it.displayName(avenger.name)
            it.displayRealName(avenger.realName)
            it.displayHeight(avenger.height)
            it.displayAbilities(avenger.abilities)
            it.displayPowers(avenger.power)
            it.displayGroups(avenger.groups)
        }
    }

    fun navUpSelected() {
        view.goBack()
    }
}