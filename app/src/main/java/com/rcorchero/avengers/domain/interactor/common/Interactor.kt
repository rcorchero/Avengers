package com.rcorchero.avengers.domain.interactor.common

import com.rcorchero.avengers.data.exception.NetworkConnectionException
import com.rcorchero.avengers.data.exception.ServiceException
import com.rcorchero.avengers.domain.model.DomainModel
import com.rcorchero.avengers.domain.model.errors.ApiError
import com.rcorchero.avengers.domain.model.errors.GenericError
import com.rcorchero.avengers.domain.model.errors.NoConnectionError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class Interactor {

    @Throws(Exception::class)
    abstract suspend fun doIt(): DomainModel

    fun execute(
        onSuccess: (DomainModel) -> Unit,
        onNoConnection: () -> Unit,
        onApiError: (String) -> Unit,
        onGenericError: () -> Unit
    ) {
        val result = GlobalScope.async {
            try {
                doIt()
            } catch (e: NetworkConnectionException) {
                e.printStackTrace()
                NoConnectionError()
            } catch (e: ServiceException) {
                e.printStackTrace()
                ApiError(e.message ?: "")
            } catch (e: Exception) {
                e.printStackTrace()
                GenericError()
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            when (result.await()) {
                is NoConnectionError -> onNoConnection()
                is ApiError -> onApiError((result.await() as ApiError).message)
                is GenericError -> onGenericError()
                else -> onSuccess(result.await() as DomainModel)
            }
        }
    }
}
