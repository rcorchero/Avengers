package com.rcorchero.avengers.data

import com.rcorchero.avengers.data.entity.AvengerEntity
import com.rcorchero.avengers.data.source.local.LocalDataSource
import com.rcorchero.avengers.data.source.remote.RemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AvengersRepositoryImplTest {

    // Class under test
    private lateinit var sut: AvengersRepositoryImpl

    @MockK
    private lateinit var localDataSource: LocalDataSource

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = AvengersRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun onGetAvengersOnlineRemoveLocalDatabaseAndCallRemoteDataSource() {
        every { remoteDataSource.getAvengers() } returns mutableListOf()

        sut.getAvengers(true)

        verify {
            remoteDataSource.getAvengers()
            localDataSource.deleteAllAvengers()
            localDataSource.saveAvengers(any())
        }
    }

    @Test
    fun onGetAvengersNotOnlineCallLocalDataSource() {
        every { localDataSource.getAvengers() } returns mutableListOf(AvengerEntity("", "", "", "", "", "", ""))

        sut.getAvengers(false)

        verify {
            localDataSource.getAvengers()
        }
    }

    @Test
    fun onGetAvengerGetItFromLocalDataSource() {
        every { localDataSource.getAvenger(any()) } returns AvengerEntity("", "", "", "", "", "", "")

        sut.getAvenger("Thor")

        verify {
            localDataSource.getAvenger(any())
        }
    }
}