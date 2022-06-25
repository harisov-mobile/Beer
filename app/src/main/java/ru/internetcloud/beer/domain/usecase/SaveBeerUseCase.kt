package ru.internetcloud.beer.domain.usecase

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.repository.BeerRepository

class SaveBeerUseCase(private val beerRepository: BeerRepository) {

    fun saveBeer(beer: Beer): Boolean {
        return beerRepository.saveBeer(beer)
    }
}
