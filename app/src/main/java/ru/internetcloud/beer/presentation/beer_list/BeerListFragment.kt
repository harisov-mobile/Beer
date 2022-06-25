package ru.internetcloud.beer.presentation.beer_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
            Toast.makeText(context, "Beer ${currentBeer.name}", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeBeerViewModel() {
        beerListViewModel.beerListLiveData.observe(viewLifecycleOwner) { list ->
            beerListAdapter.submitList(list)
        }
    }

    private fun setupBeerRecyclerView() {
        beerListAdapter = BeerListAdapter()
        binding.beerRecyclerView.adapter = beerListAdapter
        // в XML  прописано app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    }
}
