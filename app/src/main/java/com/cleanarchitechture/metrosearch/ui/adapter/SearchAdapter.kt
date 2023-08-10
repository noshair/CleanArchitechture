package com.cleanarchitechture.metrosearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitechture.databinding.SearchItemBinding


class SearchAdapter(
    private val itemClickListener: SearchItemClickListener
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    // data
    private lateinit var binding: SearchItemBinding

    inner class ViewHolder(private var item: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Int) {
            binding.apply {
                this.textId.text = item.toString()
                // tvName.text = item.name
            }
        }

    }


    interface SearchItemClickListener {
        fun itemClicked(id: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ViewHolder {
        binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }


    override fun getItemCount() = differ.currentList.size


    // reloadData()
    private val differCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}
