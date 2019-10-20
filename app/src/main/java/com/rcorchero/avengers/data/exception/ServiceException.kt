package com.rcorchero.avengers.data.exception

class ServiceException(errorMessage: String = "An error has occurred with the server"):
        Exception(errorMessage)