package com.rcorchero.avengers.presentation


interface AvengerListView : BaseView {

    fun refreshList()

    fun cancelRefreshDialog()

    fun navigateToDetailScreen(avengerName: String)
}