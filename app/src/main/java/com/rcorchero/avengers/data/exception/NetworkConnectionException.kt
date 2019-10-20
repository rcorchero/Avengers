package com.rcorchero.avengers.data.exception

class NetworkConnectionException(errorMessage: String = "The connection has failed"):
        Exception(errorMessage)