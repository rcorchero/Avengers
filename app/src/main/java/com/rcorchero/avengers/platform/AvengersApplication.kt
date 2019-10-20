package com.rcorchero.avengers.platform

import com.rcorchero.avengers.injector.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AvengersApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<AvengersApplication> {
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}