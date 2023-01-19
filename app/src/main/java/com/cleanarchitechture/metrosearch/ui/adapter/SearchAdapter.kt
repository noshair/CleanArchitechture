package com.cleanarchitechture.metrosearch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitechture.R
import com.cleanarchitechture.databinding.SearchItemBinding


class SearchAdapter(
    private val itemClickListener: SearchItemClickListener
) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    // data
    private lateinit var recyclerViewList: List<Int>

    inner class SearchViewHolder(private var searchItemBinding: SearchItemBinding) :
        RecyclerView.ViewHolder(searchItemBinding.root) {
        fun bind(item: Int) {
            searchItemBinding.textId.text = item.toString()

        }
    }


    interface SearchItemClickListener {
        fun itemClicked(id: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val view: SearchItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_item, parent, false
            )
        return SearchViewHolder(view)
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(recyclerViewList[position])

          holder.itemView.setOnClickListener {
              itemClickListener.itemClicked(recyclerViewList[position])
          }
    }


    override fun getItemCount(): Int {
        if (::recyclerViewList.isInitialized) {
            return recyclerViewList.size
        }
        return 0
    }

    // reloadData()
    fun update(users: List<Int>) {
        recyclerViewList = kotlin.collections.ArrayList(users)
        notifyDataSetChanged()
    }

}
