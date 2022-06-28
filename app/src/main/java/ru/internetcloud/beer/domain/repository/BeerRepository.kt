package ru.internetcloud.beer.domain.repository

import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.State

interface BeerRepository {

    // suspend fun getAllBeers(): Result<List<Beer>>
    suspend fun getAllBeers(): State<List<Beer>>

    fun saveBeer(beer: Beer): Boolean

    fun removeBeer(beer: Beer): Boolean

    fun getFavoriteBeers(): List<Beer>
}
