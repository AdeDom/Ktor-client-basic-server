package com.adedom.thepharak.acc.model

import kotlinx.serialization.Serializable

@Serializable
data class StaffResponse(
    var success: Boolean = false,
    var message: String? = null,
    var logo: String? = null,
    var staffList: List<Staff> = emptyList(),
)
