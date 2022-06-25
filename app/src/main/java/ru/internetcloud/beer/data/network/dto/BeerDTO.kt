package ru.internetcloud.beer.data.network.dto

import com.google.gson.annotations.SerializedName

data class BeerDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("image_url")
    val image: String,

    @SerializedName("abv")
    val abv: Double,

    @SerializedName("food_pairing")
    val foodPairing: List<String>
) {
}