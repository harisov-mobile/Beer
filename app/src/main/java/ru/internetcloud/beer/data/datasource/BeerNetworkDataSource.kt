package ru.internetcloud.beer.data.datasource

import ru.internetcloud.beer.data.mapper.BeerMapper
import ru.internetcloud.beer.data.network.api.ApiService
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.Result
import javax.inject.Inject

const val MAX_RESULTS_PER_PAGE: Int = 4

class BeerNetworkDataSource @Inject constructor(
    private val apiService: ApiService,
    private val beerMapper: BeerMapper
) {

    suspend fun getAllBeers(page: Int): Result<List<Beer>> {
        return try {
            val listBeerDTO = apiService.getAllBeers(page.toString(), MAX_RESULTS_PER_PAGE.toString())
            Result.success(beerMapper.fromListDTOToListEntity(listBeerDTO))
        } catch (e: Exception) {
            Result.error(e)
        }
    }
}
