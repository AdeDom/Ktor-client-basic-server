package com.adedom.thepharak.acc.model

import kotlinx.serialization.Serializable

@Serializable
data class FullNameRequest(
    val firstName: String,
    val lastName: String,
)
