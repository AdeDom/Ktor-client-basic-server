package com.adedom.thepharak.acc.model

import kotlinx.serialization.Serializable

@Serializable
data class Staff(
    val fullName: String? = null,
    val nickName: String? = null,
    val position: String? = null,
    val imgUrl: String? = null,
)
