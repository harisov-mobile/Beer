package ru.internetcloud.beer.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.internetcloud.beer.data.network.dto.BeerDTO

interface ApiService {

    @GET("beers?")
    // @GET("beers?brewed_before=11-1012&abv_gt=6")
    suspend fun getAllBeers(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): List<BeerDTO>
}
