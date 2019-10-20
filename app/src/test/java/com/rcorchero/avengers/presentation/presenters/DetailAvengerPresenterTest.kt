package com.rcorchero.avengers.presentation.presenters

import com.rcorchero.avengers.domain.interactor.GetAvengerInteractor
import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.domain.model.DomainModel
import com.rcorchero.avengers.presentation.DetailAvengerView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DetailAvengerPresenterTest {

    // Class under test
    private lateinit var sut: DetailAvengerPresenter

    @MockK
    private lateinit var getAvengerInteractor: GetAvengerInteractor

    @MockK
    private lateinit var view: DetailAvengerView

    @MockK
    private lateinit var avenger: Avenger

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { avenger.photo } returns ""
        every { avenger.name } returns ""
        every { avenger.realName } returns ""
        every { avenger.height } returns ""
        every { avenger.abilities } returns ""
        every { avenger.power } returns ""
        every { avenger.groups } returns ""
        every { view.getAvengerName() } returns ""

        sut = DetailAvengerPresenter(getAvengerInteractor)
        sut.attachView(view)
    }

    @Test
    fun onViewAttachedShowLoading() {
        verify { view.showLoading() }
    }

    @Test
    fun onViewAttachedExecuteGetAvengerInteractorOnlyOnce() {
        verify(exactly = 1) { getAvengerInteractor.execute(any(), any(), any(), any()) }
    }

    @Test
    fun onSuccessShowDataIntoView() {
        val callback = slot<(DomainModel) -> Unit>()

        verify { getAvengerInteractor.execute(capture(callback), any(), any(), any()) }

        callback.captured.invoke(avenger)

        verify {
            view.let {
                it.hideLoading()
                it.displayImage(avenger.photo)
                it.displayName(avenger.name)
                it.displayRealName(avenger.realName)
                it.displayHeight(avenger.height)
                it.displayAbilities(avenger.abilities)
                it.displayPowers(avenger.power)
                it.displayGroups(avenger.groups)
            }
        }
    }
}