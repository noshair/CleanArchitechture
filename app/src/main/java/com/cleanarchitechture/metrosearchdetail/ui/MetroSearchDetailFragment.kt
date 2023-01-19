package com.cleanarchitechture.metrosearchdetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cleanarchitechture.R
import com.cleanarchitechture.databinding.FragmentMetroSearchDetailBinding
import com.cleanarchitechture.extension.Resource
import com.cleanarchitechture.metrosearchdetail.ui.adapter.SearchDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MetroSearchDetailFragment : Fragment() {
    private lateinit var binding: FragmentMetroSearchDetailBinding
    private var factory: SearchDetailAdapter? = null
    private val searchViewModel: MetroSearchDetailViewModel by lazy {
        ViewModelProvider(this)[MetroSearchDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("id")?.let { searchViewModel.getSelectedItem(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_metro_search_detail, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerview()
        lifecycleScope.launchWhenStarted {
            searchViewModel.searchDetailItem.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is Resource.OnFailure -> {
                            if (it.error != null) {
                                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                            }
                        }
                        is Resource.OnSuccess -> {
                            if (it.data != null) {
                                if (it.data.primaryImage != "")
                                    Glide.with(this@MetroSearchDetailFragment)
                                        .load(it.data.primaryImage)
                                        .placeholder(R.drawable.ic_launcher_foreground)
                                        .into(binding.primaryImage)
                                else {
                                    binding.primaryImage.setImageResource(R.drawable.ic_launcher_foreground)
                                }

                                binding.title.text =
                                    context?.resources?.getString(R.string.title, it.data.title)
                                        ?: ""
                                binding.objectName.text = context?.resources?.getString(
                                    R.string.objectName,
                                    it.data.objectName
                                ) ?: ""
                                binding.department.text = context?.resources?.getString(
                                    R.string.department,
                                    it.data.department
                                ) ?: ""
                                if (it.data.additionalImages.isNotEmpty()) {
                                    factory?.update(it.data.additionalImages)
                                }
                            }
                        }
                        is Resource.OnLoading -> {
                        }
                        else -> {}
                    }
                }
        }

    }

    private fun initRecyclerview() {
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
    }
}