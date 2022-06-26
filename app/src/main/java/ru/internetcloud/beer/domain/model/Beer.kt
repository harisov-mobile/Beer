package ru.internetcloud.beer.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val alcoholPercentage: Double,
    var isFavorite: Boolean = false,
    val foodPairing: List<String>
) : Parcelable
{
    fun getAlcoholRange(alcoholPercentage: Double): AlcoholRangeType {
        return when {
            alcoholPercentage < 5 -> AlcoholRangeType.LOW
            alcoholPercentage >= 5 && alcoholPercentage < 8 -> AlcoholRangeType.NORMAL
            else -> AlcoholRangeType.HIGH
        }
    }
}
