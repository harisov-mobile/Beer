package ru.internetcloud.beer.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.internetcloud.beer.presentation.beer_list.BeerListFragment
import ru.internetcloud.beer.presentation.edit_beer.EditBeerFragment

@Component(modules = [DataModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

    fun inject(fragment: BeerListFragment)

    fun inject(fragment: EditBeerFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
