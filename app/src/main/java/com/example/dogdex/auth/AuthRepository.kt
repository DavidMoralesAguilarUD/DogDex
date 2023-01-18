package com.example.dogdex.auth

import com.example.dogdex.api.DogsApi
import com.example.dogdex.api.dto.SignUpDTO
import com.example.dogdex.api.dto.UserDTOMapper
import com.example.dogdex.api.makeNetworkCall
import com.example.dogdex.api.responses.ApiResponseStatus
import com.example.dogdex.model.User

class AuthRepository {
    suspend fun signUp(email:String, password:String, passwordConfirmation: String): ApiResponseStatus<User> = makeNetworkCall {

        val signUpDTO = SignUpDTO(email, password, passwordConfirmation)
        val signUpResponse = DogsApi.retrofitService.signUp(signUpDTO)
        if(!signUpResponse.isSucess){
            throw Exception (signUpResponse.message)
        }

        val userDTO = signUpResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }
}