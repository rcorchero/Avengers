package com.rcorchero.avengers.injector.detail

import com.rcorchero.avengers.domain.interactor.GetAvengerInteractor
import com.rcorchero.avengers.presentation.presenters.DetailAvengerPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    internal fun providePresenter(getAvengerInteractor: GetAvengerInteractor): DetailAvengerPresenter =
        DetailAvengerPresenter(getAvengerInteractor)
}