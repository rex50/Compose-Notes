package com.rex50.notes.data.model

import com.rex50.notes.data.enums.ErrorType
import java.lang.Exception

sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Failure(val exception: Exception, val errorType: ErrorType = ErrorType.UNDEFINED) : Result<Nothing>()
}
