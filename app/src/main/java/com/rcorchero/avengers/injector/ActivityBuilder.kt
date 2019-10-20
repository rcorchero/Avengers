package com.rcorchero.avengers.injector

import com.rcorchero.avengers.injector.detail.DetailActivityModule
import com.rcorchero.avengers.injector.list.ListActivityModule
import com.rcorchero.avengers.platform.views.AvengerListActivity
import com.rcorchero.avengers.platform.views.DetailAvengerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(ListActivityModule::class)])
    @PerActivity
    internal abstract fun bindListActivity(): AvengerListActivity

    @ContributesAndroidInjector(modules = [(DetailActivityModule::class)])
    @PerActivity
    internal abstract fun bindDetailActivity(): DetailAvengerActivity

}