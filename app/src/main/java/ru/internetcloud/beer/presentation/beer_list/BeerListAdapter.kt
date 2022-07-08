package ru.internetcloud.beer.presentation.beer_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.internetcloud.beer.databinding.ItemBeerListBinding
import ru.internetcloud.beer.domain.model.Beer
import ru.internetcloud.beer.presentation.util.loadImage

class BeerListAdapter : ListAdapter<Beer, BeerListViewHolder>(BeerDiffCallback()) {

    // для отработки нажатий на элемент списка - переменная, которая будет хранить лямбда-функцию,
    // на вход лямбда-функции в качестве параметра будет передан beer: Beer,
    // лямбда-функция ничего не возвращает (то есть Unit)
    // а первоначально переменная содержит null
    var onBeerListClickListener: ((beer: Beer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val binding = ItemBeerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val binding = holder.binding as ItemBeerListBinding

        val beer = getItem(position)

        binding.beerNameTextView.text = beer.name
        binding.taglineTextView.text = beer.tagline
        binding.beerImageView.loadImage(beer.image)

        binding.root.setOnClickListener {
            onBeerListClickListener?.invoke(beer)
        }
    }
}
