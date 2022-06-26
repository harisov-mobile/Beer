package ru.internetcloud.beer.presentation.edit_beer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.internetcloud.beer.databinding.FragmentEditBeerBinding
import ru.internetcloud.beer.domain.model.Beer
import java.lang.IllegalStateException

class EditBeerFragment : Fragment() {

    private lateinit var editBeerViewModel: EditBeerViewModel
    private val args: EditBeerFragmentArgs by navArgs()

    private var _binding: FragmentEditBeerBinding? = null
    val binding: FragmentEditBeerBinding
        get() = _binding ?: throw IllegalStateException("FragmentEditBeerBinding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEditBeerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editBeerViewModel = ViewModelProvider(this).get(EditBeerViewModel::class.java)

        editBeerViewModel.beer ?: let {
            // это новый экран, т.к. viewModel.beer не проинициализирована
            readArgs()
        }

        // этот блок должен специально идти ниже чем readArgs, чтобы updateUI в любом случае
        editBeerViewModel.beer?.let { currentBeer ->
            // при повороте экрана - надо заново отобразить
            updateUI(currentBeer)
        } ?: throw IllegalStateException("Beer in editBeerViewModel can not be null")
    }

    private fun updateUI(beer: Beer) {
        binding.beerNameTextView.text = beer.name
        binding.taglineTextView.text = beer.tagline
    }

    private fun readArgs() {
        editBeerViewModel.beer = args.beer
    }
}
