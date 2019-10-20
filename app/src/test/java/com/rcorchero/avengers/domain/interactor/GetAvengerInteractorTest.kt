package com.rcorchero.avengers.domain.interactor

import com.rcorchero.avengers.domain.model.Avenger
import com.rcorchero.avengers.domain.repository.AvengersRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAvengerInteractorTest {

    // Class under test
    private lateinit var sut: GetAvengerInteractor

    @MockK
    private lateinit var repository: AvengersRepository

    @MockK
    private lateinit var avenger: Avenger

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = GetAvengerInteractor(repository)
        sut.setParams("Thor")
    }

    @Test
    fun onGetAvengerCallGetRemoteObjectOnlyOnce() {
        every { repository.getAvenger(any()) } returns avenger

        runBlocking { sut.doIt() }

        verify(exactly = 1) { repository.getAvenger(any()) }
    }

    @Test
    fun onGetAvengerObjectResultIsCorrect() {
        every { repository.getAvenger(any()) } returns avenger

        val result = runBlocking { sut.doIt() }

        Assert.assertEquals(avenger, result)
    }
}