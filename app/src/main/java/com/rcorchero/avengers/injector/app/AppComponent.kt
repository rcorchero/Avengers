package com.rcorchero.avengers.injector.app

import android.app.Application
import com.rcorchero.avengers.injector.ActivityBuilder
import com.rcorchero.avengers.platform.AvengersApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class])
interface AppComponent : AndroidInjector<AvengersApplication> {

    override fun inject(app: AvengersApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}