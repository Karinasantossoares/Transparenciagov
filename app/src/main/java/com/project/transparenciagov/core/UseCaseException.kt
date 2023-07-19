package com.project.transparenciagov.core

class UseCaseException(val messageError: String): Exception(messageError)
object EmptyListException: Exception()