package com.example.dogdex.doglist

import com.example.dogdex.Dog
import com.example.dogdex.api.DogsApi.retrofitService
import com.example.dogdex.api.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository {
    suspend fun downloadDogs():List<Dog>{
        return withContext(Dispatchers.IO){
           val dogListApiResponse = retrofitService.getAllDogs()
            val dogDTOList = dogListApiResponse.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOlistToDogDomainList(dogDTOList)
        }
    }
}