package ru.internetcloud.beer.domain.usecase

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.State
import ru.internetcloud.beer.domain.repository.BeerRepository
import javax.inject.Inject

class GetAllBeersUseCase @Inject constructor(private val beerRepository: BeerRepository) {

    // suspend fun getAllBeers(): Result<List<Beer>> {
    suspend fun getAllBeers(): State<List<Beer>> {
        return beerRepository.getAllBeers()
    }
}
