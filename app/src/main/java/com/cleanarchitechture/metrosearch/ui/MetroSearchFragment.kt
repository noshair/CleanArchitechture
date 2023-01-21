package com.cleanarchitechture.metrosearch.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleanarchitechture.R
import com.cleanarchitechture.databinding.FragmentMetroSearchBinding
import com.cleanarchitechture.metrosearch.ui.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MetroSearchFragment : Fragment(), SearchAdapter.SearchItemClickListener {
    private lateinit var searchBinding: FragmentMetroSearchBinding
    private var factory: SearchAdapter? = null
    private val searchViewModel: MetroSearchViewModel by lazy {
        ViewModelProvider(this)[MetroSearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_metro_search, container, false
        )
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerview()
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            searchViewModel.searchList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it.isLoading && it.coinList.isEmpty()) {
                        searchBinding.progressBar.visibility = View.VISIBLE
                        searchBinding.hint.visibility = View.GONE
                        searchBinding.recyclerList.visibility = View.GONE
                    } else if (it.error.isNotBlank()) {
                        searchBinding.progressBar.visibility = View.GONE
                        searchBinding.recyclerList.visibility = View.GONE
                        searchBinding.hint.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    } else if (it.coinList.isNotEmpty()) {
                        searchBinding.recyclerList.visibility = View.VISIBLE
                        searchBinding.progressBar.visibility = View.GONE
                        searchBinding.hint.visibility = View.GONE
                        factory?.update(it.coinList)
                    }
                }
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchViewModel.getGalleryIds(query)
                    searchBinding.progressBar.visibility = View.VISIBLE
                    searchView.clearFocus()

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initRecyclerview() {
        searchBinding.recyclerList.apply {
            layoutManager = LinearLayoutManager(context)
            this.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            factory = SearchAdapter(this@MetroSearchFragment)
            this.addItemDecoration(
                DividerItemDecoration(
                    context, DividerItemDecoration.VERTICAL
                )
            )
            adapter = factory
        }
    }

    override fun itemClicked(id: Int) {
        val bundle = bundleOf("id" to id)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(
            R.id.action_metroSearchFragment_to_metroSearchDetailFragment,
            bundle
        )
    }

}