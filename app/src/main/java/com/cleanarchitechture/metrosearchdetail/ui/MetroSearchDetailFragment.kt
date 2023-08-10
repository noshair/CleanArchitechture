package com.cleanarchitechture.metrosearchdetail.ui

//import com.cleanarchitechture.metrosearchdetail.ui.adapter.SearchDetailAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.cleanarchitechture.R
import com.cleanarchitechture.databinding.FragmentMetroSearchDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MetroSearchDetailFragment : Fragment() {
    private var _binding: FragmentMetroSearchDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //private var factory: SearchDetailAdapter? = null
    private val searchViewModel: MetroSearchDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("id")?.let { searchViewModel.getSelectedItem(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMetroSearchDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // initRecyclerview()
        lifecycleScope.launch {
            searchViewModel.searchDetailItem.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else if (it.error.isNotEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    } else if (it.ItemList != null) {
                        binding.progressBar.visibility = View.GONE
                        Glide.with(this@MetroSearchDetailFragment)
                            .load(it.ItemList.primaryImage)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(binding.primaryImage)
                        binding.title.text =
                            context?.resources?.getString(R.string.title, it.ItemList.title)
                                ?: ""
                        binding.objectName.text = context?.resources?.getString(
                            R.string.objectName,
                            it.ItemList.objectName
                        ) ?: ""
                        binding.department.text = context?.resources?.getString(
                            R.string.department,
                            it.ItemList.department
                        ) ?: ""
                        //factory?.update(it.ItemList.additionalImages)
                    }
                }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    /*   private fun initRecyclerview() {
           binding.recyclerDetailList.apply {
               layoutManager = LinearLayoutManager(context)
               this.layoutManager = LinearLayoutManager(
                   requireContext(), LinearLayoutManager.HORIZONTAL, false
               )
               factory = SearchDetailAdapter(context)
               this.addItemDecoration(
                   DividerItemDecoration(
                       context, DividerItemDecoration.VERTICAL
                   )
               )
               adapter = factory
           }
       }*/
}