package ru.internetcloud.beer.presentation.beer_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.domain.model.State
import ru.internetcloud.beer.domain.usecase.GetAllBeersUseCase
import javax.inject.Inject

class BeerListViewModel @Inject constructor(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State<List<Beer>>>()
    val state: LiveData<State<List<Beer>>>
        get() = _state

    init {
        fetchBeers()

        // TODO 1) надо свайп вниз обрабатывать, чтобы обновить список (сделать еще раз запрос к API)
        // TODO 2) надо ловить, появился ли интернет, если его не было и он появился - обновить список
        // TODO 3) переделать на State
        // TODO 4) сделать многостраничную пагинацию
    }

    fun fetchBeers() {
        viewModelScope.launch {
            _state.value = getAllBeersUseCase.getAllBeers()
        }
    }
}
