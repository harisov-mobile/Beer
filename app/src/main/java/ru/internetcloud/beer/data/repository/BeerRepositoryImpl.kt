package ru.internetcloud.beer.data.repository

import ru.internetcloud.beer.data.datasource.BeerNetworkDataSource
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.State
import ru.internetcloud.beer.domain.repository.BeerRepository
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val beerNetworkDataSource: BeerNetworkDataSource
) : BeerRepository {

    // override suspend fun getAllBeers(): Result<List<Beer>> {
    override suspend fun getAllBeers(): State<List<Beer>> {
        // сначала просто без локального кеша, без пагинации читаем из API и показываем:
        return beerNetworkDataSource.getAllBeers(1)
    }

    override fun saveBeer(beer: Beer): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeBeer(beer: Beer): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFavoriteBeers(): List<Beer> {
        TODO("Not yet implemented")
    }
}
