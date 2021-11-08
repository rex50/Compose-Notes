package com.rex50.notes.data.model

data class Response(
    val msg: String = "",
    val success: Boolean
)

data class DataResponse<T>(
    val data: T,
    val msg: String = "",
    val success: Boolean
)