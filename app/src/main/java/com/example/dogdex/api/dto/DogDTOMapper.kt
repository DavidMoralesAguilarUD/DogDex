package com.example.dogdex.api.dto

import com.example.dogdex.model.Dog

class DogDTOMapper {
    private fun fromDogDTOToDogDomain(dogDTO: DogDTO): Dog {
        return Dog(
            dogDTO.id,dogDTO.index, dogDTO.name, dogDTO.type, dogDTO.heightFemale,
            dogDTO.heightMale, dogDTO.imageUrl,dogDTO.lifeExpectancy, dogDTO.temperament, dogDTO.weightFemale,
            dogDTO.weightMale
        )
    }
    fun fromDogDTOlistToDogDomainList(dogDTOList: List<DogDTO>): List<Dog>{
        return dogDTOList.map {
            fromDogDTOToDogDomain(it)
        }
    }
}