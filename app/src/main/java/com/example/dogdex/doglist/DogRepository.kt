package com.example.dogdex.doglist

import com.example.dogdex.Dog
import com.example.dogdex.R
import com.example.dogdex.api.DogsApi.retrofitService
import com.example.dogdex.api.dto.DogDTOMapper
import com.example.dogdex.api.makeNetworkCall
import com.example.dogdex.api.responses.ApiResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class DogRepository {
    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getAllDogs()
        val dogDTOList = dogListApiResponse.data.dogs
        val dogDTOMapper = DogDTOMapper()
        dogDTOMapper.fromDogDTOlistToDogDomainList(dogDTOList)
    }
}