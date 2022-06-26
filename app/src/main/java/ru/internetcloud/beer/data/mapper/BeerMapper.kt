package ru.internetcloud.beer.data.mapper

import ru.internetcloud.beer.data.network.dto.BeerDTO
import ru.internetcloud.beer.domain.model.Beer
import javax.inject.Inject

class BeerMapper @Inject constructor() {
    fun fromListDTOToListEntity(listBeersDTO: List<BeerDTO>): List<Beer> = listBeersDTO.map {
        fromDTOToEntity(it)
    }

    private fun fromDTOToEntity(beerDTO: BeerDTO): Beer {
        return Beer(
            id = beerDTO.id,
            name = beerDTO.name,
            tagline = beerDTO.tagline,
            image = beerDTO.image,
            alcoholPercentage = beerDTO.abv,
            foodPairing = beerDTO.foodPairing
        )
    }
}
