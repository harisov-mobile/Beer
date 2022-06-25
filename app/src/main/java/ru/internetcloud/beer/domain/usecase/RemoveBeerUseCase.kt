package ru.internetcloud.beer.domain.usecase

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.repository.BeerRepository

class RemoveBeerUseCase(private val beerRepository: BeerRepository) {

    fun removeBeer(beer: Beer): Boolean {
        return beerRepository.removeBeer(beer)
    }
}
