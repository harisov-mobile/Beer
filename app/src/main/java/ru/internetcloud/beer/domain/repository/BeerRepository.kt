package ru.internetcloud.beer.domain.repository

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.Result

interface BeerRepository {

    suspend fun getAllBeers(): Result<List<Beer>>

    fun saveBeer(beer: Beer): Boolean

    fun removeBeer(beer: Beer): Boolean

    fun getFavoriteBeers(): List<Beer>
}
