package ru.internetcloud.beer.presentation.beer_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException
import ru.internetcloud.beer.databinding.FragmentBeerListBinding

class BeerListFragment: Fragment() {

    private var _binding: FragmentBeerListBinding? = null
    val binding: FragmentBeerListBinding
        get() = _binding ?: throw IllegalStateException("FragmentBeerListBinding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }


}