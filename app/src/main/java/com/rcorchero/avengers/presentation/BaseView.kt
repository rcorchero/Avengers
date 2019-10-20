package com.rcorchero.avengers.presentation

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showConnectionError()

    fun showDefaultError()

    fun showError(errorDescription: String)
}