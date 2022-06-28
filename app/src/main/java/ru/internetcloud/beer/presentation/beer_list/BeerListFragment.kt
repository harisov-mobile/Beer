package ru.internetcloud.beer.presentation.beer_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.internetcloud.beer.BeerApp
import ru.internetcloud.beer.databinding.FragmentBeerListBinding
import ru.internetcloud.beer.di.ViewModelFactory
import ru.internetcloud.beer.domain.model.State
import java.lang.IllegalStateException
import javax.inject.Inject
import ru.internetcloud.beer.R

class BeerListFragment : Fragment() {

    // даггер:
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component by lazy {
        (requireActivity().application as BeerApp).component
    }

    private var _binding: FragmentBeerListBinding? = null
    val binding: FragmentBeerListBinding
        get() = _binding ?: throw IllegalStateException("FragmentBeerListBinding is null")

    private lateinit var beerListViewModel: BeerListViewModel
    private lateinit var beerListAdapter: BeerListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // даггер:
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // beerListViewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        beerListViewModel = ViewModelProvider(this, viewModelFactory).get(BeerListViewModel::class.java)

        setupBeerRecyclerView()
        observeBeerViewModel()
        setupClickListeners()
        // setHasOptionsMenu(true)
    }

    private fun setupClickListeners() {
        beerListAdapter.onBeerListClickListener = { currentBeer ->
            val direction = BeerListFragmentDirections.actionBeerListFragmentToEditBeerFragment(
                beer = currentBeer
            )

            findNavController().navigate(direction)
        }
    }

    private fun observeBeerViewModel() {

        beerListViewModel.state.observe(viewLifecycleOwner) { currentState ->
            binding.emptyListTextView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.errorTextView.visibility = View.GONE

            when (currentState) {
                is State.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is State.Success -> {
                    currentState.data?.let { list ->
                        if (list.size == 0) {
                            binding.emptyListTextView.visibility = View.VISIBLE
                        } else {
                            beerListAdapter.submitList(currentState.data)
                        }
                    }
                }

                is State.Error -> {
                    binding.errorTextView.text = getString(
                        R.string.error_message, currentState.exception?.message.toString())
                    binding.errorTextView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupBeerRecyclerView() {
        beerListAdapter = BeerListAdapter()
        binding.beerRecyclerView.adapter = beerListAdapter
        // в XML  прописано app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    }
}
