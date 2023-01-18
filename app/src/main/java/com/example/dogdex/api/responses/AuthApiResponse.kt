package com.example.dogdex.api.responses

import com.squareup.moshi.Json

class AuthApiResponse (

    val message: String,
    @field:Json(name = "is_success") val isSucess: Boolean,
    val data: UserResponse,
)