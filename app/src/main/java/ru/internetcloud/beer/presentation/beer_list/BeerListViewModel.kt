package ru.internetcloud.beer.presentation.beer_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.internetcloud.beer.data.datasource.BeerLocalDataSource
import ru.internetcloud.beer.data.datasource.BeerNetworkDataSource
import ru.internetcloud.beer.data.mapper.BeerMapper
import ru.internetcloud.beer.data.network.api.ApiClient
import ru.internetcloud.beer.data.repository.BeerRepositoryImpl
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.ResultType
import ru.internetcloud.beer.domain.usecase.GetAllBeersUseCase

class BeerListViewModel : ViewModel() {

    private val _beerListLiveData = MutableLiveData<List<Beer>>(emptyList())
    val beerListLiveData: LiveData<List<Beer>>
        get() = _beerListLiveData

    init {
        fetchBeers()
    }

    fun fetchBeers() {
        Log.i("rustam", "fetchBeers")

        val apiClient = ApiClient()

        val beerNetworkDataSource = BeerNetworkDataSource(apiClient.client, BeerMapper())

        val repository = BeerRepositoryImpl(beerNetworkDataSource, BeerLocalDataSource())

        viewModelScope.launch(Dispatchers.IO) {
            val result = GetAllBeersUseCase(repository).getAllBeers()
            when (result.resultType) {
                ResultType.SUCCESS -> {
                    result.data?.let { list ->
                        _beerListLiveData.postValue(list)
                    } ?: let {
                        // отобразить что ничего не найдено
                    }
                }
                ResultType.ERROR -> {
                    // отобразить ошибку
                }
            }
        }
    }
}
