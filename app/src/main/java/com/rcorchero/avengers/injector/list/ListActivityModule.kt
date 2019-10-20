package com.rcorchero.avengers.injector.list

import com.rcorchero.avengers.domain.interactor.GetAvengersInteractor
import com.rcorchero.avengers.presentation.presenters.AvengerListPresenter
import dagger.Module
import dagger.Provides

@Module
class ListActivityModule {

    @Provides
    internal fun providePresenter(getAvengersInteractor: GetAvengersInteractor): AvengerListPresenter
            = AvengerListPresenter(getAvengersInteractor)
}