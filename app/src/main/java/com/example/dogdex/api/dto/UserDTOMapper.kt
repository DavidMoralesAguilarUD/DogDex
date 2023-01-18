package com.example.dogdex.api.dto

import com.example.dogdex.model.User

class UserDTOMapper {
    fun fromUserDTOToUserDomain(userDTO: UserDTO) =
        User(userDTO.id, userDTO.email, userDTO.authenticationToken)


}