package ru.internetcloud.beer.presentation.beer_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.internetcloud.beer.databinding.FragmentBeerListBinding
import java.lang.IllegalStateException

class BeerListFragment : Fragment() {

    private var _binding: FragmentBeerListBinding? = null
    val binding: FragmentBeerListBinding
        get() = _binding ?: throw IllegalStateException("FragmentBeerListBinding is null")

    private lateinit var beerListViewModel: BeerListViewModel
    private lateinit var beerListAdapter: BeerListAdapter

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

        beerListViewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)

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
        beerListViewModel.beerListLiveData.observe(viewLifecycleOwner) { list ->
            beerListAdapter.submitList(list)
        }

        beerListViewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        beerListViewModel.isEmptyLiveData.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                binding.emptyListTextView.visibility = View.VISIBLE
            } else {
                binding.emptyListTextView.visibility = View.GONE
            }
        }

        beerListViewModel.isErrorLiveData.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                binding.errorTextView.visibility = View.VISIBLE
            } else {
                binding.errorTextView.visibility = View.GONE
            }
        }

        beerListViewModel.shouldShowRecyclerViewLiveData.observe(viewLifecycleOwner) { shouldShow ->
            if (shouldShow) {
                binding.beerRecyclerView.visibility = View.VISIBLE
            } else {
                binding.beerRecyclerView.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupBeerRecyclerView() {
        beerListAdapter = BeerListAdapter()
        binding.beerRecyclerView.adapter = beerListAdapter
        // в XML  прописано app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    }
}
