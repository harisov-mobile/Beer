package ru.internetcloud.beer.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.internetcloud.beer.presentation.beer_list.BeerListViewModel
import ru.internetcloud.beer.presentation.edit_beer.EditBeerViewModel

@Module
interface ViewModelModule {

    // перечислить тут все вью-модели
    @IntoMap
    @ViewModelKey(BeerListViewModel::class)
    @Binds
    fun bindBeerListViewModel(impl: BeerListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(EditBeerViewModel::class)
    @Binds
    fun bindEditBeerViewModel(impl: EditBeerViewModel): ViewModel
}
