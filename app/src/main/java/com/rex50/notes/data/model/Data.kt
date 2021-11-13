package com.rex50.notes.data.model

import com.rex50.notes.data.enums.ErrorType

sealed class Data<out T: Any> {
    object Loading : Data<Nothing>()

    class Ready<out T: Any>(val data: T): Data<T>()

    class Error(
        val msg: String,
        val errorType: ErrorType = ErrorType.UNDEFINED
    ) : Data<Nothing>()
}