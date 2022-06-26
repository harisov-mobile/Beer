package ru.internetcloud.beer

import android.app.Application
import ru.internetcloud.beer.di.DaggerApplicationComponent

class BeerApp : Application() {

    // помни про манифест: android:name=".BeerApp"

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
