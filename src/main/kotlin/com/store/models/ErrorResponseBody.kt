package com.store.models

import java.time.LocalDateTime

data class ErrorResponseBody(
    val timestamp: String = LocalDateTime.now().toString(),
    val status: Int,
    val error: String,
    val path: String
)
