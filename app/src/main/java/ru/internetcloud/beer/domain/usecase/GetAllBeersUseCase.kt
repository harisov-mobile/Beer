package ru.internetcloud.beer.domain.usecase

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.Result
import ru.internetcloud.beer.domain.repository.BeerRepository

class GetAllBeersUseCase(private val beerRepository: BeerRepository) {

    suspend fun getAllBeers(): Result<List<Beer>> {
        return beerRepository.getAllBeers()
    }
}
