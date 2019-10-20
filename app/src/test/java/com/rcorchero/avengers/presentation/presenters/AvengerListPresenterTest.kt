package com.rcorchero.avengers.presentation.presenters

import com.rcorchero.avengers.domain.interactor.GetAvengersInteractor
import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.domain.model.Avengers
import com.rcorchero.avengers.domain.model.DomainModel
import com.rcorchero.avengers.presentation.AvengerListView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AvengerListPresenterTest {

    // Class under test
    private lateinit var sut: AvengerListPresenter

    @MockK
    private lateinit var getAvengersInteractor: GetAvengersInteractor

    @MockK
    private lateinit var view: AvengerListView

    @MockK
    private lateinit var avengers: Avengers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = AvengerListPresenter(getAvengersInteractor)
        sut.attachView(view)
    }

    @Test
    fun onViewAttachedShowLoading() {
        verify { view.showLoading() }
    }

    @Test
    fun onViewAttachedExecuteGetAvengersInteractorWithOnlyOnlineNotSet() {
        verify {
            getAvengersInteractor.setParams(false)
            getAvengersInteractor.execute(any(), any(), any(), any())
        }
    }

    @Test
    fun onRefreshExecuteGetAvengersInteractorWithOnlyOnlineSet() {
        sut.refresh()

        verify {
            getAvengersInteractor.setParams(true)
            getAvengersInteractor.execute(any(), any(), any(), any())
        }
    }

    @Test
    fun onSuccessShowDataIntoView() {
        every { avengers.avengers } returns mutableListOf()

        val callback = slot<(DomainModel) -> Unit>()

        verify { getAvengersInteractor.execute(capture(callback), any(), any(), any()) }

        callback.captured.invoke(avengers)

        verify {
            view.let {
                it.hideLoading()
                it.cancelRefreshDialog()
                it.refreshList()
            }
        }
    }

    @Test
    fun onItemClickNavigateToDetailScreen() {
        sut.avengerList = mutableListOf(Avenger("", "", "", "", "", "", ""))

        sut.onItemClick(0)

        verify { view.navigateToDetailScreen(any()) }
    }
}