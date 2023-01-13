package com.example.dogdex.api.responses

import com.squareup.moshi.Json

class DogListApiResponse(
    val message: String,
    @field:Json(name = "is_success") val isSucess: Boolean,
    val data: DogListResponse,
)
