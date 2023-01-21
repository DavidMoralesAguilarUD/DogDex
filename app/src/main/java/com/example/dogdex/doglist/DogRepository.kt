package com.example.dogdex.doglist

import com.example.dogdex.R
import com.example.dogdex.model.Dog
import com.example.dogdex.api.DogsApi.retrofitService
import com.example.dogdex.api.dto.DogDTOMapper
import com.example.dogdex.api.makeNetworkCall
import com.example.dogdex.api.ApiResponseStatus
import com.example.dogdex.api.dto.AddDogToUserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DogRepository {

    suspend fun getDogCollection():ApiResponseStatus<List<Dog>>{
        return withContext(Dispatchers.IO){
            val allDogsListDeferred = async { downloadDogs()}
            val userDogsListDeferred  = async {getUserDogs()}

            val allDogsListResponse  = allDogsListDeferred.await()
            val userDogsListResponse = userDogsListDeferred.await()

            if(allDogsListResponse is ApiResponseStatus.Error){
                allDogsListResponse
            } else if(userDogsListResponse is ApiResponseStatus.Error){
                userDogsListResponse
            } else if(allDogsListResponse is ApiResponseStatus.Success &&
                userDogsListResponse is ApiResponseStatus.Success){
                ApiResponseStatus.Success(getCollectionList(allDogsListResponse.data,
                    userDogsListResponse.data))
            } else {
                ApiResponseStatus.Error(R.string.unknown_error)
            }
        }

    }
    private  fun getCollectionList(allDogList :List<Dog>, userDogList: List<Dog>) =
        allDogList.map{
            if(userDogList.contains(it)){
                it
            }else{
                Dog(0,it.index, "","","","","","","",
                    "", "", inCollection = false )
            }
        }.sorted()


    private suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getAllDogs()
        val dogDTOList = dogListApiResponse.data.dogs
        val dogDTOMapper = DogDTOMapper()
        dogDTOMapper.fromDogDTOlistToDogDomainList(dogDTOList)
    }

    private suspend fun getUserDogs():ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getUserDogs()
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