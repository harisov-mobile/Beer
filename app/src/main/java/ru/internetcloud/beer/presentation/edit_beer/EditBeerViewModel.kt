package ru.internetcloud.beer.presentation.edit_beer

import androidx.lifecycle.ViewModel
import ru.internetcloud.beer.domain.model.Beer
import javax.inject.Inject

class EditBeerViewModel @Inject constructor() : ViewModel() {

    var beer: Beer? = null
}
