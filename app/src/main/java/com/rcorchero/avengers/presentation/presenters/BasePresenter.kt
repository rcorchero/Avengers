package com.rcorchero.avengers.presentation.presenters

import com.rcorchero.avengers.domain.model.DomainModel
import com.rcorchero.avengers.domain.interactor.common.Interactor
import com.rcorchero.avengers.presentation.BaseView

abstract class BasePresenter<V : BaseView> {

    protected lateinit var view: V

    fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    abstract fun onViewAttached()

    fun execute(interactor: Interactor,
                onSuccess: (DomainModel) -> Unit,
                onNoConnection: () -> Unit = this::noConnectionError,
                onApiError: (String) -> Unit = this::onApiError,
                onGenericError: () -> Unit = this::onGenericError) {
        interactor.execute(onSuccess, this::noConnectionError, this::onApiError, this::onGenericError)
    }

    private fun noConnectionError() {
        view.hideLoading()
        view.showConnectionError()
    }

    private fun onApiError(errorDescription: String) {
        view.hideLoading()
        view.showError(errorDescription)
    }

    private fun onGenericError() {
        view.hideLoading()
        view.showDefaultError()
    }
}