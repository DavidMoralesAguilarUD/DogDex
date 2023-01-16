package com.example.dogdex.api.responses

import com.example.dogdex.Dog

sealed class ApiResponseStatus() {
    class Success(val dogList: List<Dog>): ApiResponseStatus()
    class Loading(): ApiResponseStatus()
    class Error(val messageId:Int): ApiResponseStatus()

}