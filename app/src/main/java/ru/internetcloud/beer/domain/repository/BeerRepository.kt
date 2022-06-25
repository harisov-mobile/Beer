package ru.internetcloud.beer.domain.repository

import ru.internetcloud.beer.domain.model.Beer

interface BeerRepository {

    fun getAllBeers(): Result<List<Beer>>

    fun saveBeer(beer: Beer): Boolean

    fun removeBeer(beer: Beer): Boolean

    fun getFavoriteBeers(): List<Beer>
}
