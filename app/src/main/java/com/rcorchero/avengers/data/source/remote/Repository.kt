package com.rcorchero.avengers.data.source.remote

import com.rcorchero.avengers.data.exception.NetworkConnectionException
import com.rcorchero.avengers.data.exception.ServiceException
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

open class Repository(protected var apiService: APIService) {

    companion object {
        const val CODE_INTERNAL_SERVER_ERROR = 500
    }

    @Throws(Exception::class)
    fun executeCall(call: Call<*>): Response<*> {
        try {
            val response = call.execute() ?: throw IOException()
            if (!response.isSuccessful) throwExceptionApi(response)
            return response
        } catch (e: IOException) {
            throw NetworkConnectionException()
        }
    }

    @Throws(Exception::class)
    private fun throwExceptionApi(response: Response<*>): Unit =
        when {
            response.code() == CODE_INTERNAL_SERVER_ERROR -> throw ServiceException()
            else -> throw ServiceException(response.message())
        }
}