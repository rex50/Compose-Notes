package com.rex50.notes.data.model

import java.io.Serializable

data class Response<T>(
    val data: T? = null,
    val msg: String = "",
    val success: Boolean = false
): Serializable