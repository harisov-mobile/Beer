package ru.internetcloud.beer.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.internetcloud.beer.data.network.api.ApiClient
import ru.internetcloud.beer.data.network.api.ApiService
import ru.internetcloud.beer.data.repository.BeerRepositoryImpl
import ru.internetcloud.beer.domain.repository.BeerRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindBeerRepository(impl: BeerRepositoryImpl): BeerRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiClient(): ApiService {
            return ApiClient().client
        }
    }
}
