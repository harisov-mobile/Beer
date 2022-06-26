package ru.internetcloud.beer.presentation.beer_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.ResultType
import ru.internetcloud.beer.domain.usecase.GetAllBeersUseCase
import javax.inject.Inject

class BeerListViewModel @Inject constructor(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : ViewModel() {

    private val _beerListLiveData = MutableLiveData<List<Beer>>()
    val beerListLiveData: LiveData<List<Beer>>
        get() = _beerListLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    private val _isEmptyLiveData = MutableLiveData<Boolean>()
    val isEmptyLiveData: LiveData<Boolean>
        get() = _isEmptyLiveData

    private val _shouldShowRecyclerViewLiveData = MutableLiveData<Boolean>()
    val shouldShowRecyclerViewLiveData: LiveData<Boolean>
        get() = _shouldShowRecyclerViewLiveData

    init {
        fetchBeers()

        // TODO 1) надо свайп вниз обрабатывать, чтобы обновить список (сделать еще раз запрос к API)
        // TODO 2) надо ловить, появился ли интернет, если его не было и он появился - обновить список
        // TODO 3) переделать на State
    }

    fun fetchBeers() {

        viewModelScope.launch() {
            _shouldShowRecyclerViewLiveData.value = false
            _isErrorLiveData.value = false
            _isEmptyLiveData.value = false
            _isLoadingLiveData.value = true
            val result = getAllBeersUseCase.getAllBeers()
            _isLoadingLiveData.value = false
            when (result.resultType) {
                ResultType.SUCCESS -> {
                    result.data?.let { list ->
                        if (list.isEmpty()) {
                            _isEmptyLiveData.value = true
                        } else {
                            _beerListLiveData.postValue(list)
                            _shouldShowRecyclerViewLiveData.value = true
                        }
                    } ?: let {
                        // отобразить что ничего не найдено
                        _isEmptyLiveData.value = true
                    }
                }
                ResultType.ERROR -> {
                    // отобразить ошибку
                    _isErrorLiveData.value = true
                }
            }
        }
    }
}
