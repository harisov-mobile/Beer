package ru.internetcloud.beer.presentation.beer_list

import androidx.recyclerview.widget.DiffUtil
import ru.internetcloud.beer.domain.model.Beer

class BeerDiffCallback : DiffUtil.ItemCallback<Beer>() {

    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }
}
