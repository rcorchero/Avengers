package com.rcorchero.avengers.presentation.presenters

import androidx.annotation.VisibleForTesting
import com.rcorchero.avengers.domain.model.DomainModel
import com.rcorchero.avengers.domain.interactor.GetAvengersInteractor
import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.domain.model.Avengers
import com.rcorchero.avengers.presentation.AvengerCellView
import com.rcorchero.avengers.presentation.AvengerListView
import javax.inject.Inject

class AvengerListPresenter
@Inject constructor(private val getAvengersInteractor: GetAvengersInteractor): BasePresenter<AvengerListView>() {

    @VisibleForTesting
    var avengerList = emptyList<Avenger>()

    private var selectedAvengerName: String = ""

    override fun onViewAttached() {
        view.showLoading()

        getAvengersInteractor.setParams(false)
        execute(getAvengersInteractor, this::onSuccess, this::onError)
    }

    fun refresh() {
        getAvengersInteractor.setParams(true)
        execute(getAvengersInteractor, this::onSuccess, this::onError)
    }

    private fun onSuccess(data: DomainModel) {
        view.hideLoading()

        val avengers = data as Avengers

        saveAvengers(avengers.avengers)
        view.let {
            it.cancelRefreshDialog()
            it.refreshList()
        }
    }

    private fun onError() {
        view.let {
            it.cancelRefreshDialog()
            it.showDefaultError()
        }
    }

    fun getItemsCount(): Int =
        if(avengersListIsEmpty())
            0
        else
            avengerList.size

    fun configureCell(avengerCellView: AvengerCellView, position: Int) {
        val avenger = getAvenger(position)
        avengerCellView.displayImage(avenger.photo)
    }

    fun onItemClick(position: Int) {
        val avenger = getAvenger(position)
        saveSelectedAvengerName(avenger.name)
        view.navigateToDetailScreen(getSelectedAvengerName())
    }

    private fun saveAvengers(avengerList: List<Avenger>) {
        this.avengerList = avengerList
    }

    private fun getAvenger(position: Int): Avenger = avengerList[position]

    private fun saveSelectedAvengerName(selectedAvengerName: String) {
        this.selectedAvengerName = selectedAvengerName
    }

    private fun avengersListIsEmpty(): Boolean = avengerList.isEmpty()

    private fun getSelectedAvengerName(): String = selectedAvengerName
}