package com.rcorchero.avengers.domain.interactor

import com.rcorchero.avengers.domain.model.Avengers
import com.rcorchero.avengers.domain.repository.AvengersRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAvengersInteractorTest{

    // Class under test
    private lateinit var sut: GetAvengersInteractor

    @MockK
    private lateinit var repository: AvengersRepository

    @MockK
    private lateinit var avengers: Avengers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = GetAvengersInteractor(repository)
        sut.setParams(true)
    }

    @Test
    fun onGetAvengersCallGetRemoteObjectOnlyOnce() {
        every { repository.getAvengers(any()) } returns avengers

        runBlocking { sut.doIt() }

        verify(exactly = 1) { repository.getAvengers(any()) }
    }

    @Test
    fun onGetAvengersObjectResultIsCorrect() {
        every { repository.getAvengers(any()) } returns avengers

        val result = runBlocking { sut.doIt() }

        Assert.assertEquals(avengers, result)
    }
}