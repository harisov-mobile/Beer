package ru.internetcloud.beer.data.repository

import ru.internetcloud.beer.data.datasource.BeerLocalDataSource
import ru.internetcloud.beer.data.datasource.BeerNetworkDataSource
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.Result
import ru.internetcloud.beer.domain.repository.BeerRepository

class BeerRepositoryImpl(
    private val beerNetworkDataSource: BeerNetworkDataSource,
    private val beerCacheDataSource: BeerLocalDataSource
) : BeerRepository {

    override suspend fun getAllBeers(): Result<List<Beer>> {
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
