package ru.internetcloud.beer.domain.usecase

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.repository.BeerRepository

class GetAllBeersUseCase(private val beerRepository: BeerRepository) {

    fun getAllBeers(): Result<List<Beer>> {
        return beerRepository.getAllBeers()
    }
}
