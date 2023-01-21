package com.example.dogdex.doglist

import com.example.dogdex.model.Dog
import com.example.dogdex.api.DogsApi.retrofitService
import com.example.dogdex.api.dto.DogDTOMapper
import com.example.dogdex.api.makeNetworkCall
import com.example.dogdex.api.ApiResponseStatus
import com.example.dogdex.api.dto.AddDogToUserDTO

class DogRepository {
    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getAllDogs()
        val dogDTOList = dogListApiResponse.data.dogs
        val dogDTOMapper = DogDTOMapper()
        dogDTOMapper.fromDogDTOlistToDogDomainList(dogDTOList)
    }

    suspend fun addDogToUser(dogId:Long):ApiResponseStatus<Any> = makeNetworkCall {
        val addDogToUserDTO = AddDogToUserDTO(dogId)
        val defaultResponse  = retrofitService.addDogToUser(addDogToUserDTO)

        if(!defaultResponse.isSuccess){
            throw Exception(defaultResponse.message)
        }
    }
}